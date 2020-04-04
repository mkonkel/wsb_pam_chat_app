package pl.mkonkel.wsb.pam.chatapp.repository.open

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import pl.mkonkel.wsb.pam.chatapp.AppInjector
import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository
import pl.mkonkel.wsb.pam.chatapp.repository.Repository
import pl.mkonkel.wsb.pam.chatapp.repository.loggedIn.LoggedInRepo

internal class PublicRepo(private val context: Context) : Repository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun isSessionActive(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun register(email: String, password: String, callback: Repository.AuthCallback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    AppInjector.bindLoggedInRepo(createLoggedInRepository())

                    callback.onSuccess()
                } else {
                    callback.onFailure()
                }
            }

    }

    override fun login(email: String, password: String, callback: Repository.AuthCallback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    AppInjector.bindLoggedInRepo(createLoggedInRepository())

                    callback.onSuccess()
                } else {
                    callback.onFailure()
                }
            }
    }

    private fun createLoggedInRepository(): LoggedInRepository {
        return LoggedInRepo(context, firebaseAuth)
    }
}