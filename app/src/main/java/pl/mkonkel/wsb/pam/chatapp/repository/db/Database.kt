package pl.mkonkel.wsb.pam.chatapp.repository.db

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository
import pl.mkonkel.wsb.pam.chatapp.repository.model.Token
import timber.log.Timber

class Database(context: Context) {
    private val db = FirebaseDatabase.getInstance()

    fun addToken(id: String, token: Token, callback: LoggedInRepository.Callback<Unit>?) {
        db.reference.child(TOKENS).child(id)
            .setValue(token)
            .addOnSuccessListener {
                Timber.i("SUCCESS!")
                callback?.onSuccess()
            }
            .addOnFailureListener {
                Timber.e(it)
                callback?.onFailure(it)
            }
    }

    private companion object {
        const val TOKENS = "tokens"
    }
}