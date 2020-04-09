package pl.mkonkel.wsb.pam.chatapp.presentation.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.mkonkel.wsb.pam.chatapp.R
import pl.mkonkel.wsb.pam.chatapp.domain.model.Message
import timber.log.Timber

class ChatListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = ArrayList<Message>()

    class ViewHolderIncomming(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(
            inflater.inflate(
                R.layout.item_chat_message_incomming,
                parent,
                false
            )
        ) {
        private val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        private val message: TextView = itemView.findViewById(R.id.chat_message)

        fun bind(element: Message) {
            timestamp.text = element.timestamp
            message.text = element.message
        }
    }

    class ViewHolderOngoing(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.item_chat_message_outgoing,
            parent,
            false
        )
    ) {
        private val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        private val message: TextView = itemView.findViewById(R.id.chat_message)

        fun bind(element: Message) {
            timestamp.text = element.timestamp
            message.text = element.message
        }
    }

    class ViewHolderError(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.item_chat_message_error,
            parent,
            false
        )
    ) {
        private val error: TextView = itemView.findViewById(R.id.error)

        fun bind(element: Message) {
            error.text = element.message ?: "Something went wrong!"
        }
    }

    fun addItem(item: Message) {
        this.items.add(item)

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type.toInt()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType.toMessageType()) {
            Message.Type.INCOMMING -> ViewHolderIncomming(inflater, parent)
            Message.Type.OUTGOING -> ViewHolderOngoing(inflater, parent)
            Message.Type.ERROR,
            Message.Type.UNKNOWN -> ViewHolderError(inflater, parent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val element = items[position]
        when (holder) {
            is ViewHolderIncomming -> {
                holder.bind(element)
            }
            is ViewHolderOngoing -> {
                holder.bind(element)
            }
            is ViewHolderError -> {
                holder.bind(element)
            }
            else -> {
                Timber.e("Unrecognized ViewHolder!")
            }
        }
    }

    private fun Message.Type.toInt(): Int {
        return when (this) {
            Message.Type.INCOMMING -> 1
            Message.Type.OUTGOING -> 0
            else -> -1
        }
    }

    private fun Int.toMessageType(): Message.Type {
        return when (this) {
            1 -> Message.Type.INCOMMING
            0 -> Message.Type.OUTGOING
            else -> {
                Message.Type.UNKNOWN
            }
        }
    }
}