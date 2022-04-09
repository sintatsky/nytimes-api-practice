package com.sintatsky.astest.presentation.bottom_items

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sintatsky.astest.databinding.FragmentSupportBinding
import com.sintatsky.astest.domain.entity.SupportMessage
import com.sintatsky.astest.presentation.adapters.ChatBotAdapter
import com.sintatsky.astest.utils.BotResponse
import com.sintatsky.astest.utils.Constants.OPEN_GOOGLE
import com.sintatsky.astest.utils.Constants.OPEN_SEARCH
import com.sintatsky.astest.utils.Constants.RECEIVE_ID
import com.sintatsky.astest.utils.Constants.SEND_ID
import com.sintatsky.astest.utils.Time
import kotlinx.coroutines.*


class SupportFragment : Fragment() {
    private var _binding: FragmentSupportBinding? = null
    private val binding: FragmentSupportBinding
        get() = _binding ?: throw RuntimeException("FragmentSupportBinding is null")

    private lateinit var chatBotAdapter: ChatBotAdapter
    private val botList = listOf("Peter", "Francesca", "Luigi", "Igor")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (savedInstanceState == null) {
            _binding = FragmentSupportBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChatRecyclerView()

        initClickEvents()

        val random = (0..3).random()
        customMessage("Hello! Today you're speaking with ${botList[random]}, how can I help?")
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                binding.rvMessages.scrollToPosition(chatBotAdapter.itemCount - 1)
            }
        }
    }

    private fun initClickEvents(){
        binding.btnSend.setOnClickListener {
            sendMessage()
        }
        binding.etMessage.setOnClickListener {
            lifecycleScope.launch {
                delay(100)
                withContext(Dispatchers.Main) {
                    binding.rvMessages.scrollToPosition(chatBotAdapter.itemCount - 1)
                }
            }
        }
    }

    private fun initChatRecyclerView() {
        chatBotAdapter = ChatBotAdapter()
        binding.rvMessages.adapter = chatBotAdapter
        binding.rvMessages.layoutManager = LinearLayoutManager(requireContext().applicationContext)
    }

    private fun sendMessage() {
        val message = binding.etMessage.text.toString()
        val timestamp = Time.timeStamp()
        if (message.isNotEmpty()) {
            binding.etMessage.setText("")
            chatBotAdapter.insertMessage(SupportMessage(SEND_ID, message, timestamp))
            binding.rvMessages.scrollToPosition(chatBotAdapter.itemCount - 1)
            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timestamp = Time.timeStamp()
        lifecycleScope.launch {
            delay(1000)
            val response = BotResponse.basicResponses(message)
            chatBotAdapter.insertMessage(SupportMessage(RECEIVE_ID, response, timestamp))
            binding.rvMessages.scrollToPosition(chatBotAdapter.itemCount - 1)
            when (response) {
                OPEN_GOOGLE -> {
                    val site = Intent(Intent.ACTION_VIEW)
                    site.data = Uri.parse("https://www.google.com/")
                    startActivity(site)
                }
                OPEN_SEARCH -> {
                    val site = Intent(Intent.ACTION_VIEW)
                    val searchTerm: String = message.substringAfter("search")
                    site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                    startActivity(site)
                }
            }
        }
    }


    private fun customMessage(message: String) {
        lifecycleScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                chatBotAdapter.insertMessage(SupportMessage(RECEIVE_ID, message, timeStamp))
                binding.rvMessages.scrollToPosition(chatBotAdapter.itemCount - 1)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = SupportFragment()
    }
}