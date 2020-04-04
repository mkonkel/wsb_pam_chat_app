package pl.mkonkel.wsb.pam.chatapp

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_auth.*
import pl.mkonkel.wsb.pam.chatapp.login.RegisterFragment

class AuthActivity : BaseActivity() {
    private lateinit var loginFragment: Fragment
    private lateinit var registerFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        registerFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_register) ?: RegisterFragment()

        loginFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_login) ?: RegisterFragment()

        showLogin()
    }

    private fun showLogin() {
        toggleFragment(loginFragment, registerFragment)

        val topRow = "Don't have account yet?!\n\n"
        val bottomRow = "REGISTER NOW!"

        navigation_text.setSpannedText(topRow, bottomRow)
        navigation_text.setOnClickListener {
            showRegister()
        }
    }

    private fun showRegister() {
        toggleFragment(registerFragment, loginFragment)

        val topRow = "Got account?\n\n"
        val bottomRow = "LOGIN NOW!"

        navigation_text.setSpannedText(topRow, bottomRow)
        navigation_text.setOnClickListener {
            showLogin()
        }
    }

    private fun toggleFragment(fragmentToShow: Fragment?, fragmentToHide: Fragment?) {
        if (fragmentToShow != null && fragmentToHide != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.show(fragmentToShow)
            ft.hide(fragmentToHide)
            ft.commit()
        }
    }

    private fun TextView.setSpannedText(notSpanned: String, textToSpan: String) {
        val wholeText = "$notSpanned$textToSpan"
        val spannable = SpannableString(wholeText)

        spannable.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.red)),
            wholeText.indexOf(textToSpan),
            wholeText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        this.text = spannable
    }
}