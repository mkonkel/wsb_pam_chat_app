package pl.mkonkel.wsb.pam.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    fun start(screen: Screen, selfClose: Boolean = false) {
        val intent: Intent = when (screen) {
            Screen.MAIN -> {
                Intent(this, MainActivity::class.java)
            }
            Screen.AUTH -> {
                Intent(this, AuthActivity::class.java)
            }
        }

        this.startActivity(intent)
        if (selfClose) {
            finish()
        }
    }


    enum class Screen {
        MAIN,
        AUTH
    }
}