package pl.mkonkel.wsb.pam.chatapp.presentation.chat

import android.os.Bundle
import pl.mkonkel.wsb.pam.chatapp.MyFirebaseMessagingService
import pl.mkonkel.wsb.pam.chatapp.R
import pl.mkonkel.wsb.pam.chatapp.presentation.BaseActivity

class ChatActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        intent?.let {
            val title = it.getStringExtra(MyFirebaseMessagingService.NOTIFICATION_MESSAGE_TITLE)
            val body = it.getStringExtra(MyFirebaseMessagingService.NOTIFICATION_MESSAGE_BODY)
        }
    }
}