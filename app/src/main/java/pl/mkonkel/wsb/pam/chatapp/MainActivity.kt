package pl.mkonkel.wsb.pam.chatapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val firebaseAuth: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (!firebaseAuth) {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        } else {
            Timber.i("Should show Chat Main Screen")
        }
    }
}
