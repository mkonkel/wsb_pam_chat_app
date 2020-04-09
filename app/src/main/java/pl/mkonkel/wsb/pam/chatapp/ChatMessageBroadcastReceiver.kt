package pl.mkonkel.wsb.pam.chatapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

class ChatMessageBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let {
            val title = it.getStringExtra(MyFirebaseMessagingService.NOTIFICATION_MESSAGE_TITLE)
            val body = it.getStringExtra(MyFirebaseMessagingService.NOTIFICATION_MESSAGE_BODY)


            val sendIntent = Intent(ACTION)
            val extras = Bundle()
            extras.putString(MESSAGE_TITLE, title)
            extras.putString(MESSAGE_BODY, body)

            sendIntent.putExtras(extras)
            context.sendBroadcast(sendIntent)
        }
    }

    companion object {
        const val ACTION = "chat_message_action"
        const val MESSAGE_TITLE = "message_title"
        const val MESSAGE_BODY = "message_body"
    }
}