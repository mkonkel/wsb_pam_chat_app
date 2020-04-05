package pl.mkonkel.wsb.pam.chatapp.repository

import com.google.firebase.auth.FirebaseUser
import pl.mkonkel.wsb.pam.chatapp.repository.model.Token

interface LoggedInRepository {
    fun user(): FirebaseUser?
    fun logout(): Unit
    fun addFcmToken(fcmToken: String, callback: Callback<Unit>? = null)
    fun getTokens(callback: Callback<List<Token>>)

    interface Callback<TYPE> {
        fun onSuccess(value: TYPE)
        fun onFailure(throwable: Throwable)
    }
}