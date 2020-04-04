package pl.mkonkel.wsb.pam.chatapp.presentation.auth

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_base_auth.*
import pl.mkonkel.wsb.pam.chatapp.domain.AuthService
import pl.mkonkel.wsb.pam.chatapp.repository.Repository

class LoginFragment : BaseAuthFragment() {
    private val authService = AuthService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hello_text.text = "Welcome back!\nPlease log in!"
        submit_button.setOnClickListener {
            if (validate()) {
                login()
            }
        }
    }

    private fun login() {
        toggleProgress()

        authService.login(
            email(),
            password(),
            authCallback
        )
    }

    private val authCallback = object : Repository.AuthCallback {
        override fun onSuccess() {
            toggleProgress()
            fragmentListener?.onSuccess()
        }

        override fun onFailure() {
            toggleProgress()
            fragmentListener?.onFailure()
        }
    }
}