package com.sintatsky.astest.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sintatsky.astest.databinding.ChatBotItemBinding
import com.sintatsky.astest.domain.entity.review.SupportMessage
import com.sintatsky.astest.utils.Constants.RECEIVE_ID
import com.sintatsky.astest.utils.Constants.SEND_ID

class ChatBotAdapter : RecyclerView.Adapter<ChatBotAdapter.ChatBotViewHolder>() {
    var messagesList = mutableListOf<SupportMessage>()

    class ChatBotViewHolder(val binding: ChatBotItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatBotViewHolder {
        val binding = ChatBotItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatBotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatBotViewHolder, position: Int) {
        val currentMessage = messagesList[position]
        with(holder.binding) {
            when (currentMessage.id) {
                SEND_ID -> {
                    tvMessage.apply {
                        text = currentMessage.message
                        visibility = View.VISIBLE
                    }
                    tvBotMessage.visibility = View.GONE
                }
                RECEIVE_ID -> {
                    tvBotMessage.apply {
                        text = currentMessage.message
                        visibility = View.VISIBLE
                    }
                    holder.binding.tvMessage.visibility = View.GONE
                }
            }
            root.setOnClickListener {
                messagesList.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    override fun getItemCount() = messagesList.size

    fun insertMessage(message: SupportMessage) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
        notifyDataSetChanged()
    }
}