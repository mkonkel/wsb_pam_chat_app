package pl.mkonkel.wsb.pam.chatapp.domain.utils

import com.google.firebase.auth.FirebaseAuthInvalidUserException

object ErrorResolver {
    fun handle(throwable: Throwable?): String {
        fun fallback(): String {
            return "Something went wrong"
        }

        return when (throwable) {
            is FirebaseAuthInvalidUserException -> {
                throwable.message ?: fallback()
            }
            else -> {
                fallback()
            }
        }
    }
}