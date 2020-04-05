package pl.mkonkel.wsb.pam.chatapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.mkonkel.wsb.pam.chatapp.R
import pl.mkonkel.wsb.pam.chatapp.repository.model.Token

class TokenListAdapter(private val onTokenClick: (Token) -> Unit) :
    RecyclerView.Adapter<TokenListAdapter.ViewHolder>() {

    private var items = ArrayList<Token>()


    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(R.layout.item_token_list, parent, false)
    ) {
        private val nickname: TextView? = itemView.findViewById(R.id.nickname)

        fun bind(element: Token, position: Int, onTokenClick: (Token) -> Unit) {
            nickname?.text = element.nickname
            itemView.setOnClickListener {
                onTokenClick(element)
            }

            val resources = this.itemView.context.resources

            if (position.rem(2) == 0) {
                itemView.setBackgroundColor(resources.getColor(R.color.colorPrimary, null))
            } else {
                itemView.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark, null))
            }
        }
    }

    fun setItems(items: List<Token>) {
        this.items.clear()
        items?.let {
            this.items.addAll(it)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position, onTokenClick)
    }
}