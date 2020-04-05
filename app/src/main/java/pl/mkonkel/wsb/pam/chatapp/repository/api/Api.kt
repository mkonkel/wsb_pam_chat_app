package pl.mkonkel.wsb.pam.chatapp.repository.api

interface Api {
    fun sendPush(fcmToken: String, title: String, message: String, callback: Callback<Unit>)

    interface Callback<T> {
        fun onSuccess(response: T)
        fun onError(error: Throwable)
    }
}