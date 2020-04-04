package pl.mkonkel.wsb.pam.chatapp.repository.model

data class Token(
    val nickname: String?,
    val token: String
) {
    constructor() : this("", "")
}