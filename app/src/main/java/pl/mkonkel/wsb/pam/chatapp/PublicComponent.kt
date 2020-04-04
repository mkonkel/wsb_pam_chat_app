package pl.mkonkel.wsb.pam.chatapp

import pl.mkonkel.wsb.pam.chatapp.domain.AuthService
import pl.mkonkel.wsb.pam.chatapp.repository.Repository

interface PublicComponent {
    val repository: Repository
    fun authService(): AuthService
}

