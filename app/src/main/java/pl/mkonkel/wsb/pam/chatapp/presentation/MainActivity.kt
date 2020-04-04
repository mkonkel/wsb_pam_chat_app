package pl.mkonkel.wsb.pam.chatapp.presentation

import android.os.Bundle
import android.os.Handler
import pl.mkonkel.wsb.pam.chatapp.R
import pl.mkonkel.wsb.pam.chatapp.domain.AuthService

class MainActivity : BaseActivity() {
    private val authService = AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            checkSession()
        }, DELAY_TIME_IN_MILIS)
    }

    private fun checkSession() {
        if (authService.isLoggedIn()) {
            start(Screen.USERS_LIST)
        } else {
            start(Screen.AUTH)
        }
    }

    companion object {
        const val DELAY_TIME_IN_MILIS = 1000L
    }
}
