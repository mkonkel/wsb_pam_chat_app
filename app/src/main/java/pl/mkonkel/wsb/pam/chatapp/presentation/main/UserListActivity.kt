package pl.mkonkel.wsb.pam.chatapp.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_user_list.*
import pl.mkonkel.wsb.pam.chatapp.AppInjector
import pl.mkonkel.wsb.pam.chatapp.R
import pl.mkonkel.wsb.pam.chatapp.domain.utils.ErrorResolver
import pl.mkonkel.wsb.pam.chatapp.presentation.BaseActivity
import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository
import pl.mkonkel.wsb.pam.chatapp.repository.model.Token
import timber.log.Timber

class UserListActivity : BaseActivity() {
    private val tokenService = AppInjector.loggedInComponent.tokenService
    private var adapter: TokenListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        getTokens()
    }

    private fun getTokens() {
        tokenService.getTokenList(
            object : LoggedInRepository.Callback<List<Token>> {
                override fun onSuccess(value: List<Token>) {
                    setItems(value)
                }

                override fun onFailure(throwable: Throwable) {
                    Toast.makeText(this@UserListActivity, ErrorResolver.handle(throwable), Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    private fun setItems(elements: List<Token>) {
        if (adapter == null) {
            adapter = TokenListAdapter { onTokenClicked(it) }
        }

        (token_list as RecyclerView).also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        }

        adapter?.setItems(elements)
    }

    private fun onTokenClicked(token: Token) {
        Timber.i("Selected token: $token")
    }
}