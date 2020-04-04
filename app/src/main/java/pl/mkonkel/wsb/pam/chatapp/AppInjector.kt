package pl.mkonkel.wsb.pam.chatapp

import pl.mkonkel.wsb.pam.chatapp.domain.AuthService
import pl.mkonkel.wsb.pam.chatapp.domain.PushService
import pl.mkonkel.wsb.pam.chatapp.domain.TokenService
import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository
import pl.mkonkel.wsb.pam.chatapp.repository.Repository
import pl.mkonkel.wsb.pam.chatapp.repository.open.PublicRepo

object AppInjector {
    lateinit var publicComponent: PublicComponent
    lateinit var loggedInComponent: LoggedInComponent

    fun initialize(app: App) {
        publicComponent = object : PublicComponent {
            override val repository: Repository
                get() = PublicRepo(app)

            override fun authService(): AuthService {
                return AuthService(repository)
            }
        }
    }

    fun createLoggedInComponent(loggedInRepository: LoggedInRepository) {
        this.loggedInComponent = object : LoggedInComponent {
            override val loggedInRepo: LoggedInRepository
                get() = loggedInRepository

            override fun pushService(): PushService {
                return PushService(loggedInRepository)
            }

            override fun tokenService(): TokenService {
                return TokenService(loggedInRepository)
            }
        }
    }
}