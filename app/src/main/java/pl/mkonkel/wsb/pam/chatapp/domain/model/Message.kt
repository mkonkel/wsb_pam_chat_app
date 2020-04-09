package pl.mkonkel.wsb.pam.chatapp.domain.model

import pl.mkonkel.wsb.pam.chatapp.domain.utils.DateUtils

data class Message(
    val message: String?,
    val type: Type,
    val timestamp: String
) {
    enum class Type {
        INCOMMING,
        OUTGOING,
        ERROR,
        UNKNOWN
    }

    companion object {
        fun ofError(message: String): Message {
            return Message(
                type = Type.ERROR,
                message = message,
                timestamp = DateUtils.getLocalDateTimeInHumaanReadableFormat()
            )
        }

        fun ofIncoming(message: String): Message {
            return Message(
                type = Type.INCOMMING,
                message = message,
                timestamp = DateUtils.getLocalDateTimeInHumaanReadableFormat()
            )
        }

        fun ofOutgoing(message: String): Message {
            return Message(
                type = Type.OUTGOING,
                message = message,
                timestamp = DateUtils.getLocalDateTimeInHumaanReadableFormat()
            )
        }
    }
}