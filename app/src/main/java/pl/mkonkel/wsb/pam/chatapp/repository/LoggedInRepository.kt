package pl.mkonkel.wsb.pam.chatapp.repository

import com.google.firebase.auth.FirebaseUser

interface LoggedInRepository {
    fun user(): FirebaseUser?
    fun logout(): Unit
}