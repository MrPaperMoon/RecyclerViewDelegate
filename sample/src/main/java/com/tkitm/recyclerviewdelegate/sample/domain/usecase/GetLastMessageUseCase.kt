package com.tkitm.recyclerviewdelegate.sample.domain.usecase

import com.tkitm.recyclerviewdelegate.sample.data.repository.MessageRepository
import com.tkitm.recyclerviewdelegate.sample.domain.entity.Message

class GetLastMessageUseCase {

    private val messageRepository = MessageRepository()

    suspend operator fun invoke(chatId: String): Message? = messageRepository.getLastMessage(chatId)
}