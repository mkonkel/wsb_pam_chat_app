package pl.mkonkel.wsb.pam.chatapp.repository.loggedIn

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository
import pl.mkonkel.wsb.pam.chatapp.repository.api.Api
import pl.mkonkel.wsb.pam.chatapp.repository.db.Database
import pl.mkonkel.wsb.pam.chatapp.repository.model.Token

internal class LoggedInRepo(
    private val context: Context,
    private val firebaseAuth: FirebaseAuth,
    private val database: Database,
    private val api: Api
) : LoggedInRepository {

    override fun user(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun logout() {
        return firebaseAuth.signOut()
    }

    override fun addFcmToken(fcmToken: String, callback: LoggedInRepository.Callback<Unit>?) {
        user()?.let {
            database.addToken(it.uid, Token(nickname = it.email, token = fcmToken), callback)
        }
    }

    override fun getTokens(callback: LoggedInRepository.Callback<List<Token>>) {
        database.getTokens(callback)
    }

    override fun sendPushMessage(
        fcmToken: String,
        title: String,
        message: String,
        callback: LoggedInRepository.Callback<Unit>
    ) {
        val apiCallback = object : Api.Callback<Unit> {
            override fun onSuccess(response: Unit) {
                callback.onSuccess(response)
            }

            override fun onError(error: Throwable) {
                callback.onFailure(error)
            }
        }

        api.sendPush(
            fcmToken,
            title,
            message,
            apiCallback
        )
    }
}