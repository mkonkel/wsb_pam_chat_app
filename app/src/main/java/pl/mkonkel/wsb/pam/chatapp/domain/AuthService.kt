package pl.mkonkel.wsb.pam.chatapp.domain

import pl.mkonkel.wsb.pam.chatapp.repository.Repository

class AuthService(private val repository: Repository) {
    fun isLoggedIn(): Boolean {
        return repository.isSessionActive()
    }

    fun login(email: String, password: String, authCallback: Repository.AuthCallback) {
        repository.login(email, password, authCallback)
    }

    fun register(email: String, password: String, authCallback: Repository.AuthCallback) {
        repository.register(email, password, authCallback)
    }
}