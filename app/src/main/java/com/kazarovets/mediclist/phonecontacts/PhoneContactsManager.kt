package com.kazarovets.mediclist.phonecontacts

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentProviderOperation
import android.content.ContentProviderResult
import android.content.OperationApplicationException
import android.database.Cursor
import android.net.Uri
import android.os.RemoteException
import android.provider.ContactsContract
import android.provider.ContactsContract.*
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.ContactsContract.CommonDataKinds.StructuredName
import com.kazarovets.mediclist.persons.repo.PersonsRepository
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PhoneContactsManager @Inject constructor(
    private val app: Application,
    private val personsRepository: PersonsRepository
) {

    private val showContactsLogs: Boolean = false

    private var contactsDisposable: Disposable? = null
    @SuppressLint("CheckResult")
    fun init() {
        contactsDisposable?.dispose()
        contactsDisposable = personsRepository.getPersonsOnceAndStream()
            .map {
                it
                    .mapNotNull {
                        val number = it.phoneNumber
                        if (number != null && number.length >= 9) {
                            PersonContactData(
                                name = it.personName,
                                phone = number,
                                isClosed = it.isClosed
                            )
                        } else null
                    }
            }
            .distinctUntilChanged()
            .subscribe {
                Timber.d("contacts $it")
                updateContacts(it)
            }
    }

    private fun updateContacts(persons: List<PersonContactData>) {
        persons.filter { it.isClosed.not() }.forEach {
            addContact(it.name, it.phone)
        }
        persons.filter { it.isClosed }.forEach {
            deleteContact(it.phone, it.name)
        }
    }

    private fun addContact(name: String, phone: String) {
        logD("adding contact $name, $phone")
        val ops = ArrayList<ContentProviderOperation>()
        val rawContactInsertIndex: Int = ops.size

        ops.add(
            ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
                .withValue(RawContacts.ACCOUNT_TYPE, null)
                .withValue(RawContacts.ACCOUNT_NAME, null).build()
        )
        ops.add(
            ContentProviderOperation
                .newInsert(Data.CONTENT_URI)
                .withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
                .withValue(StructuredName.DISPLAY_NAME, name)
                .build()
        )
        ops.add(
            ContentProviderOperation
                .newInsert(Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                .withValue(Phone.NUMBER, phone)
                .withValue(Phone.TYPE, Phone.TYPE_MOBILE).build()
        )

        try {
            val res: Array<ContentProviderResult> =
                app.contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)
            logD("result inserting count = ${res.count()}")
        } catch (e: RemoteException) { // error
            logE(e, "Remote exception")
        } catch (e: OperationApplicationException) { // error
            logE(e, "Operation Application exception")
        } catch (e: Exception) {
            logE(e, "Exception adding contacts")
        }
    }

    private fun deleteContact(phone: String, name: String) {
        val contactUri: Uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone))
        val cur = app.contentResolver.query(contactUri, null, null, null, null)
        try {
            if (cur!!.moveToFirst()) {
                do {
                    val lookupKey: String = cur.getString(cur.getColumnIndex(Contacts.LOOKUP_KEY))
                    val uri: Uri = Uri.withAppendedPath(
                        Contacts.CONTENT_LOOKUP_URI,
                        lookupKey
                    )
                    logD("deleting contact phone = $phone, name = $name")
                    app.contentResolver.delete(uri, null, null)
                } while (cur.moveToNext())
            } else {
                logD("deleting contact $name, no contact found")
            }
        } catch (e: Exception) {
            logE(e, "Error deleting contact")
        } finally {
            cur?.close()
        }
    }

    private fun logD(str: String) {
        if(showContactsLogs) Timber.d(str)
    }

    private fun logE(e:java.lang.Exception, str: String) {
        if(showContactsLogs) {
            Timber.e(e, str)
        }
    }

}

data class PersonContactData(
    val name: String,
    val phone: String,
    val isClosed: Boolean
)