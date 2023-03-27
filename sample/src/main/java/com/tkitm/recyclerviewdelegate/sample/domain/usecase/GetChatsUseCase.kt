package com.tkitm.recyclerviewdelegate.sample.domain.usecase

import com.tkitm.recyclerviewdelegate.sample.data.repository.UserRepository
import com.tkitm.recyclerviewdelegate.sample.domain.entity.Chat
import kotlinx.coroutines.flow.*

class GetChatsUseCase {

    private val userRepository = UserRepository()
    private val getLastMessageUseCase = GetLastMessageUseCase()

    suspend operator fun invoke(collect: suspend (List<Chat>) -> Unit) {
        flow {
            val userList = userRepository.getUserList()
            val rawChatList = userList.map {
                val lastMessage = getLastMessageUseCase(it.id)
                Chat(it, lastMessage)
            }
            emit(rawChatList)
        }.also {
            it.collectLatest(collect)
        }
    }
}