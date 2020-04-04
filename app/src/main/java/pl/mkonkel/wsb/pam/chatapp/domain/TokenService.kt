package pl.mkonkel.wsb.pam.chatapp.domain

import pl.mkonkel.wsb.pam.chatapp.AppInjector
import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository

class TokenService(private val repository: LoggedInRepository) {
    fun addMyToken(fcmToken: String) {

    }
}