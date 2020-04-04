package pl.mkonkel.wsb.pam.chatapp.presentation.util

import android.content.Intent
import pl.mkonkel.wsb.pam.chatapp.presentation.BaseActivity

object LaunchIntentDecorator {

    fun decor(intent: Intent, screen: BaseActivity.Screen) {
        intent.putExtra("screen", screen.name)
    }
}

object LaunchIntentExtractor {
    fun getScreen(intent: Intent): BaseActivity.Screen =
        intent.getStringExtra("screen")
            ?.let { BaseActivity.Screen.valueOf(it) }
            ?: BaseActivity.Screen.UNKNOWN
}
