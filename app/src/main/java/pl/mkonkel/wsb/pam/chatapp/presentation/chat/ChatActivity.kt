package pl.mkonkel.wsb.pam.chatapp.presentation.chat

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_chat.*
import pl.mkonkel.wsb.pam.chatapp.R
import pl.mkonkel.wsb.pam.chatapp.domain.model.Message
import pl.mkonkel.wsb.pam.chatapp.presentation.BaseActivity
import timber.log.Timber

class ChatActivity : BaseActivity() {
    private var adapter: ChatListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        setItems(getRandomMEssages())

        icon_send.setOnClickListener {
            val msg = message_text.text.toString()
            message_text.setText("")

            setItem(
                Message(
                    message = msg,
                    timestamp = "now",
                    type = Message.Type.OUTGOING
                )
            )

        }
    }

    private fun setItems(elements: List<Message>) {
        if (adapter == null) {
            adapter = ChatListAdapter()
        }

        (chat_items as RecyclerView).also { rv ->
            val manager = LinearLayoutManager(this).also { it.stackFromEnd = true }
            rv.layoutManager = manager

            rv.adapter = adapter?.also {
                it.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        manager.smoothScrollToPosition(chat_items, null, it.itemCount)
                    }
                })
            }
        }

        adapter?.setItems(elements)

    }

    private fun setItem(element: Message) {
        if (adapter == null) {
            adapter = ChatListAdapter()
        }

        (chat_items as RecyclerView).also { rv ->
            val manager = LinearLayoutManager(this).also { it.stackFromEnd = true }
            rv.layoutManager = manager

            rv.adapter = adapter?.also {
                it.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        manager.smoothScrollToPosition(chat_items, null, 0)
                    }
                })
            }
        }

        adapter?.addItem(element)
    }

    private fun getRandomMEssages(): List<Message> {
        fun randomMsg(int: Int): String {
            return when (int) {
                0 -> "Al contrario di quanto si pensi, Lorem Ipsum non è semplicemente una sequenza casuale di caratteri. Risale ad un classico della letteratura latina del 45 AC"
                else -> "Esistono innumerevoli variazioni dei passaggi del Lorem Ipsum"
            }
        }

        return (1..20).map {
                Message(
                    type = if (it % 2 == 0) Message.Type.INCOMMING else Message.Type.OUTGOING,
                    message = randomMsg(it % 3),
                    timestamp = "Just now..."
                )
            }
            .onEach { Timber.i((it.type.name)) }
    }
}