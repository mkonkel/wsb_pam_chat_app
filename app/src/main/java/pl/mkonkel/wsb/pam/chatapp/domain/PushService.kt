package pl.mkonkel.wsb.pam.chatapp.domain

import com.google.firebase.iid.FirebaseInstanceId
import pl.mkonkel.wsb.pam.chatapp.repository.LoggedInRepository
import timber.log.Timber

class PushService(
    private val repository: LoggedInRepository, private val tokenService: TokenService
) {
    init {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.e("getInstanceId failed ${task.exception}")
                }


                task.result?.token?.let {
                    tokenService.addMyToken(it)
                }
            }
    }
}