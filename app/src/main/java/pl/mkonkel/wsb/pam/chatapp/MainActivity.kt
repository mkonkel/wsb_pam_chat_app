package pl.mkonkel.wsb.pam.chatapp

import android.os.Bundle
import timber.log.Timber

class MainActivity : BaseActivity() {
    private val firebaseAuth: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (!firebaseAuth) {
            start(Screen.AUTH)
        } else {
            Timber.i("Should show Chat Main Screen")
        }
    }
}
