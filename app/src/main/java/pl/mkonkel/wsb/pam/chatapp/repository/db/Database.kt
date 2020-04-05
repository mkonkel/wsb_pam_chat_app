package pl.mkonkel.wsb.pam.chatapp.repository.db

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
                callback?.onSuccess(Unit)
            }
            .addOnFailureListener {
                Timber.e(it)
                callback?.onFailure(it)
            }
    }

    fun getTokens(callback: LoggedInRepository.Callback<List<Token>>) {
        db.reference.child(TOKENS)
            .limitToFirst(100)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Timber.e(p0.toException())
                    callback.onFailure(p0.toException())
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val tokenList = p0.children
                        .mapNotNull {
                            val parsed = it.getValue(Token::class.java)
                            Timber.i("dbObject: $parsed")

                            parsed
                        }

                    callback.onSuccess(tokenList)
                }
            }
            )
    }

    private companion object {
        const val TOKENS = "tokens"
    }
}