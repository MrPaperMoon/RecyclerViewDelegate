package com.tkitm.recyclerviewdelegate.sample.presentation.presentor

import androidx.lifecycle.*
import com.tkitm.recyclerviewdelegate.RecyclerViewDelegate
import com.tkitm.recyclerviewdelegate.RecyclerViewDelegate.Action
import com.tkitm.recyclerviewdelegate.sample.domain.entity.Chat
import com.tkitm.recyclerviewdelegate.sample.domain.usecase.*
import com.tkitm.recyclerviewdelegate.sample.presentation.ui.ChatDelegateAdapter
import kotlinx.coroutines.*

class ChatsViewModel : ViewModel(), RecyclerViewDelegate.ActionListener {

    val chatListFlow: LiveData<List<ChatDelegateAdapter>>
        get() = _chatListFlow

    private val _chatListFlow = MutableLiveData<List<ChatDelegateAdapter>>()

    private val getChatsUseCase = GetChatsUseCase()
    private val getLastMessageUseCase = GetLastMessageUseCase()

    private var chatList: List<Chat> = emptyList()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getChatsUseCase { chats ->
                chatList = chats
                setChatList()
            }
        }
    }

    override fun onAction(action: Action) {
        TODO("Not yet implemented")
    }

    fun refreshData() {
        viewModelScope.launch(Dispatchers.Default) {
            chatList = chatList.mapIndexed { index, chat ->
                if (index % 2 == 0) {
                    chat.copy(lastMessage = getLastMessageUseCase(chat.user.id))
                } else {
                    chat
                }
            }
            setChatList()
        }
    }

    private fun setChatList() {
        val list = chatList
            .sortedByDescending { it.lastMessage?.date }
            .map { (user, lastMessage) ->
                ChatDelegateAdapter(user.id, null, user.name, lastMessage)
            }
        _chatListFlow.postValue(list)
    }
}