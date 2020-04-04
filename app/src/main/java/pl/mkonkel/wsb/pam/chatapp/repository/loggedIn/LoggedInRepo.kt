package pl.mkonkel.wsb.pam.chatapp.repository.loggedIn

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository

internal class LoggedInRepo(private val context: Context, private val firebaseAuth: FirebaseAuth) :
    LoggedInRepository {
    override fun user(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun logout() {
        return firebaseAuth.signOut()
    }
}