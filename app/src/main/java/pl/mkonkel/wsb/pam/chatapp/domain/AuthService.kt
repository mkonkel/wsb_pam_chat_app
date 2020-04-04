package pl.mkonkel.wsb.pam.chatapp.domain

import pl.mkonkel.wsb.pam.chatapp.AppInjector
import pl.mkonkel.wsb.pam.chatapp.repository.Repository

object AuthService {
    fun isLoggedIn(): Boolean {
        return AppInjector.repository.isSessionActive()
    }

    fun login(email: String, password: String, authCallback: Repository.AuthCallback) {
        AppInjector.repository.login(email, password, authCallback)
    }

    fun register(email: String, password: String, authCallback: Repository.AuthCallback) {
        AppInjector.repository.register(email, password, authCallback)
    }
}