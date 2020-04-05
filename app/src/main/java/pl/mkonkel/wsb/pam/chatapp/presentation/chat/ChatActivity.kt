package pl.mkonkel.wsb.pam.chatapp.presentation.chat

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chat.*
import pl.mkonkel.wsb.pam.chatapp.MyFirebaseMessagingService
import pl.mkonkel.wsb.pam.chatapp.R
import pl.mkonkel.wsb.pam.chatapp.presentation.BaseActivity

class ChatActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        intent?.let {
            push_title.text =
                it.getStringExtra(MyFirebaseMessagingService.NOTIFICATION_MESSAGE_TITLE)
            push_description.text =
                it.getStringExtra(MyFirebaseMessagingService.NOTIFICATION_MESSAGE_BODY)
        }
    }
}