package pl.mkonkel.wsb.pam.chatapp.repository

import com.google.firebase.auth.FirebaseUser

interface LoggedInRepository {
    fun user(): FirebaseUser?
    fun logout(): Unit
    fun addFcmToken(fcmToken: String, callback: Callback<Unit>? = null)

    interface Callback<T> {
        fun onSuccess(): T
        fun onFailure(throwable: Throwable)
    }
}