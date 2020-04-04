package pl.mkonkel.wsb.pam.chatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pl.mkonkel.wsb.pam.chatapp.login.RegisterFragment

class AuthActivity : AppCompatActivity() {
    private lateinit var loginFragment: Fragment
    private lateinit var registerFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        registerFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_register) ?: RegisterFragment()

        loginFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_register) ?: RegisterFragment()

        showLogin()
    }

    private fun showLogin() {
        toggleFragment(loginFragment, registerFragment)
    }

    private fun toggleFragment(fragmentToShow: Fragment?, fragmentToHide: Fragment?) {
        if (fragmentToShow != null && fragmentToHide != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.show(fragmentToShow)
            ft.hide(fragmentToHide)
            ft.commit()
        }
    }
}