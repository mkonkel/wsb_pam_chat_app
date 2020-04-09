package pl.mkonkel.wsb.pam.chatapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import pl.mkonkel.wsb.pam.chatapp.presentation.chat.ChatActivity
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val notificationManager by lazy {
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title ?: remoteMessage.data["title"] ?: ""
        val body = remoteMessage.notification?.body ?: remoteMessage.data["message"] ?: ""

//        createNotificationChannel()
//        notificationManager.notify(
//            getId(),
//            buildNotification(createPendingIntent(title, body), title)
//        )

        val intent = Intent(ChatMessageBroadcastReceiver.ACTION)
        val extras = Bundle()
        extras.putString(NOTIFICATION_MESSAGE_TITLE, title)
        extras.putString(NOTIFICATION_MESSAGE_BODY, body)

        intent.putExtras(extras)

        sendBroadcast(intent)
    }

//    private fun buildNotification(
//        pendingIntent: PendingIntent,
//        notificationTitle: String?
//    ): Notification {
//        return NotificationCompat.Builder(
//                applicationContext,
//                NOTIFICATION_CHANNEL_ID
//            )
//            .setSmallIcon(R.drawable.ic_message_black_24dp)
//            .setContentTitle("New messages:")
//            .setContentText(notificationTitle)
//            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .build()
//    }

//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationChannel = NotificationChannel(
//                NOTIFICATION_CHANNEL_ID,
//                NOTIFICATION_CHANNEL_NAME,
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//
//            notificationChannel.also {
//                it.enableLights(true)
//                it.enableVibration(true)
//                it.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
//                it.description = "Simple chanel to show notifications from Firebase"
//                it.lightColor = Color.CYAN
//            }
//
//            // Register the channel
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//    }

//    private fun createPendingIntent(title: String?, body: String?): PendingIntent {
//
//        // Create pending intent to run detailActivity
//        val resultIntent = Intent(this, ChatActivity::class.java)
//
//        resultIntent.putExtra(NOTIFICATION_MESSAGE_TITLE, title)
//        resultIntent.putExtra(NOTIFICATION_MESSAGE_BODY, body)
//        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        return PendingIntent.getActivity(
//            this,
//            NOTIFICATION_REQUEST_CODE,
//            resultIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//    }
//
//    private fun getId(): Int {
//        return Random(500).nextInt()
//    }

    companion object {
        const val NOTIFICATION_REQUEST_CODE = 666
        const val NOTIFICATION_CHANNEL_ID = "1010011010"
        const val NOTIFICATION_CHANNEL_NAME = "Default App Notification Channel"
        const val NOTIFICATION_MESSAGE_TITLE = "message_title"
        const val NOTIFICATION_MESSAGE_BODY = "message_body"
    }
}