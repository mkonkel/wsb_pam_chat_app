package pl.mkonkel.wsb.pam.chatapp.presentation.chat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_chat.*
import pl.mkonkel.wsb.pam.chatapp.AppInjector
import pl.mkonkel.wsb.pam.chatapp.ChatMessageBroadcastReceiver
import pl.mkonkel.wsb.pam.chatapp.R
import pl.mkonkel.wsb.pam.chatapp.domain.model.Message
import pl.mkonkel.wsb.pam.chatapp.domain.utils.ErrorResolver
import pl.mkonkel.wsb.pam.chatapp.presentation.BaseActivity
import pl.mkonkel.wsb.pam.chatapp.presentation.util.LaunchIntentExtractor
import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository

class ChatActivity : BaseActivity() {
    private val pushService = AppInjector.loggedInComponent.pushService

    private var adapter: ChatListAdapter? = null
    private lateinit var chatBroadcastReceiver: BroadcastReceiver
    private var fcmToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        fcmToken = LaunchIntentExtractor.getExtra(intent, FCM_TOKEN)

        setAdapter()
        registerChatReciever()

        icon_send.setOnClickListener {
            message_text.text?.let {
                if (it.toString().isNotBlank()) {
                    val message = Message.ofOutgoing(message = it.toString())
                    addMessageToRecycler(message)
                    sendMessage(message, fcmToken)
                }
            }

            message_text.setText("")
        }
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(chatBroadcastReceiver)
    }

    private fun registerChatReciever() {
        chatBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.let {
                    val title = it.getStringExtra(ChatMessageBroadcastReceiver.MESSAGE_TITLE)
                    val body = it.getStringExtra(ChatMessageBroadcastReceiver.MESSAGE_BODY)

                    if (!body.isNullOrEmpty()) {
                        val message = Message.ofIncoming(message = body)
                        addMessageToRecycler(message)
                    }
                }
            }
        }

        registerReceiver(chatBroadcastReceiver, IntentFilter(ChatMessageBroadcastReceiver.ACTION))
    }

    private fun setAdapter() {
        if (adapter == null) {
            adapter = ChatListAdapter()
        }

        (chat_items as RecyclerView).also { rv ->
            val manager = LinearLayoutManager(this).also { it.stackFromEnd = true }
            rv.layoutManager = manager

            rv.adapter = adapter?.also {
                it.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        manager.smoothScrollToPosition(chat_items, null, it.itemCount)
                    }
                })
            }
        }
    }

    private fun addMessageToRecycler(message: Message) {
        adapter?.addItem(message)
    }

    private fun sendMessage(message: Message, token: String?) {
        if (token != null) {
            pushService.sendPush(
                fcmToken = token,
                title = "",
                message = message.message ?: "",
                callback = object : LoggedInRepository.Callback<Unit> {
                    override fun onSuccess(value: Unit) {
                        Toast.makeText(
                            this@ChatActivity,
                            "sending...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onFailure(throwable: Throwable) {
                        val errorMsg = Message.ofError(ErrorResolver.handle(throwable))
                        addMessageToRecycler(errorMsg)
                    }
                }
            )
        } else {
            Toast.makeText(this@ChatActivity, "An Error occurred!", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val FCM_TOKEN = "fcm_token"
    }
}