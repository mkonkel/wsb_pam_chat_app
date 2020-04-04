package pl.mkonkel.wsb.pam.chatapp.repository

interface Repository {
    fun isSessionActive(): Boolean
    fun register(email: String, password: String, callback: AuthCallback)
    fun login(email: String, password: String, callback: AuthCallback)

    interface AuthCallback {
        fun onSuccess()
        fun onFailure()
    }
}