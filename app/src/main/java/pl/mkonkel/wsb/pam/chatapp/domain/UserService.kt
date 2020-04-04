package pl.mkonkel.wsb.pam.chatapp.domain

import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository

class UserService(private val repository: LoggedInRepository) {
    fun logout() {
        repository.logout()
    }

    fun profile() {
        repository.user()
    }
}