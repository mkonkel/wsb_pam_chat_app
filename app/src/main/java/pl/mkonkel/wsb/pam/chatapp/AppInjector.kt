package pl.mkonkel.wsb.pam.chatapp

import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository
import pl.mkonkel.wsb.pam.chatapp.repository.Repository
import pl.mkonkel.wsb.pam.chatapp.repository.open.PublicRepo

object AppInjector {
    lateinit var repository: Repository
    lateinit var loggedInRepo: LoggedInRepository

    fun initialize(app: App) {
        repository = PublicRepo(app)
    }

    fun bindLoggedInRepo(loggedInRepository: LoggedInRepository) {
        this.loggedInRepo = loggedInRepository
    }
}