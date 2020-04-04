package pl.mkonkel.wsb.pam.chatapp

import pl.mkonkel.wsb.pam.chatapp.domain.PushService
import pl.mkonkel.wsb.pam.chatapp.domain.TokenService
import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository

interface LoggedInComponent {
    val loggedInRepo: LoggedInRepository
    val pushService: PushService
    val tokenService: TokenService
}