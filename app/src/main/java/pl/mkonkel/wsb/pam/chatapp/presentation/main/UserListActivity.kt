package pl.mkonkel.wsb.pam.chatapp.presentation.main

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_auth.*
import pl.mkonkel.wsb.pam.chatapp.R
import pl.mkonkel.wsb.pam.chatapp.domain.utils.ErrorResolver
import pl.mkonkel.wsb.pam.chatapp.presentation.BaseActivity

class UserListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

    }
}