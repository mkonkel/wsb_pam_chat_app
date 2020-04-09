package pl.mkonkel.wsb.pam.chatapp.domain.model

data class Message(
    val message: String? = "",
    val type: Type,
    val timestamp: String
) {
    enum class Type {
        INCOMMING,
        OUTGOING,
        UNKNOWN
    }
}