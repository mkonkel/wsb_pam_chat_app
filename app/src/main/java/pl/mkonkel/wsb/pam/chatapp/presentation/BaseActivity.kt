package pl.mkonkel.wsb.pam.chatapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import pl.mkonkel.wsb.pam.chatapp.presentation.auth.AuthActivity
import pl.mkonkel.wsb.pam.chatapp.presentation.main.UserListActivity
import pl.mkonkel.wsb.pam.chatapp.presentation.util.LaunchIntentDecorator
import pl.mkonkel.wsb.pam.chatapp.presentation.util.LaunchIntentExtractor

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val screen = LaunchIntentExtractor.getScreen(intent)
        screenCustomSettings(screen)
        super.onCreate(savedInstanceState)

    }

    fun start(screen: Screen, selfClose: Boolean = false) {
        val intent: Intent = when (screen) {
            Screen.MAIN -> {
                Intent(this, MainActivity::class.java)
            }
            Screen.AUTH -> {
                Intent(this, AuthActivity::class.java)
            }
            Screen.USERS_LIST -> {
                Intent(this, UserListActivity::class.java)
            }
            Screen.UNKNOWN -> {
                throw IllegalStateException("Unknown activity")
            }
        }

        LaunchIntentDecorator.decor(intent, screen)
        this.startActivity(intent)
        if (selfClose) {
            finish()
        }
    }

    private fun screenCustomSettings(screen: Screen) {
        when (screen) {
            Screen.MAIN -> {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                )
            }
            else -> {

            }
        }
    }

    enum class Screen {
        MAIN,
        AUTH,
        USERS_LIST,
        UNKNOWN
    }
}