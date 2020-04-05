package pl.mkonkel.wsb.pam.chatapp.presentation.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_base_auth.*
import pl.mkonkel.wsb.pam.chatapp.R
import pl.mkonkel.wsb.pam.chatapp.domain.utils.InputCheck
import timber.log.Timber

open class BaseAuthFragment : Fragment() {
    interface FragmentAuthCallback {
        fun onSuccess()
        fun onFailure(throwable: Throwable?)
    }

    protected var fragmentListener: FragmentAuthCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        email_layout.clearErrorAfterTyping()
        password_layout.clearErrorAfterTyping()
    }

    fun validate(): Boolean {
        Timber.i("Validating form!")

        val isEmailValid = email().let {
            if (InputCheck.isEmail(it)) {
                Timber.i("Valid email")
                true
            } else {
                Timber.i("Invalid email $it")
                email_layout.error = "This is not a valid email address"
                false
            }
        }

        val isPasswordValid = password().let {
            if (InputCheck.minLength(it)) {
                Timber.i("Valid password")
                true
            } else {
                Timber.i("Invalid password!")
                password_layout.error = "Password must be at least 6 characters long!"
                false
            }
        }

        return isEmailValid && isPasswordValid
    }

    fun email(): String {
        return email_layout.editText?.text?.toString() ?: ""
    }

    fun password(): String {
        return password_layout.editText?.text?.toString() ?: ""
    }

    private fun TextInputLayout.clearErrorAfterTyping() {
        this.editText?.addTextChangedListener { this.error = null }
    }

    protected fun toggleProgress() {
        progress.visibility = progress.visibility.let {
            if (it == View.GONE) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentListener = context as? FragmentAuthCallback
        if (fragmentListener == null) {
            throw ClassCastException("$context must implement OnArticleSelectedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.fragmentListener = null
    }
}