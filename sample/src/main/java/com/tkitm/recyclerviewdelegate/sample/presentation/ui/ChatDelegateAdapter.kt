package com.tkitm.recyclerviewdelegate.sample.presentation.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.*
import com.tkitm.recyclerviewdelegate.RecyclerViewDelegate
import com.tkitm.recyclerviewdelegate.sample.R
import com.tkitm.recyclerviewdelegate.sample.domain.entity.Message
import com.tkitm.recyclerviewdelegate.sample.presentation.utils.getDateFormat

data class ChatDelegateAdapter(private val chatId: String, private val avatar: Drawable?, private val title: String, private val lastMessage: Message?) : RecyclerViewDelegate() {

    override val layoutId: Int
        get() = R.layout.vh_chat

    private val onClickChat by lazy { OnClickChat(chatId) }

    override fun ViewHolder.onBindViewHolder(context: Context, adapterPosition: Int) {
        itemView.findViewById<ImageView>(R.id.chat_row_avatar)
            .setImageDrawable(avatar)
        itemView.findViewById<TextView>(R.id.chat_row_title).text = title

        itemView.findViewById<TextView>(R.id.chat_row_date).text = lastMessage?.date?.getDateFormat()
        itemView.findViewById<TextView>(R.id.chat_row_message_preview).text = lastMessage?.text

        itemView.setOnClickListener {
            actionListener?.onAction(onClickChat)
        }
    }

    data class OnClickChat(val id: String) : Action
}