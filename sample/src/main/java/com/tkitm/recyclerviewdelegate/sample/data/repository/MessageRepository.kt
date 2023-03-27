package com.tkitm.recyclerviewdelegate.sample.data.repository

import com.github.javafaker.*
import com.tkitm.recyclerviewdelegate.sample.domain.entity.Message
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit.DAYS

class MessageRepository {

    private val idService: IdNumber
    private val quoteService: Friends
    private val dateService: DateAndTime

    init {
        val faker = Faker()
        idService = faker.idNumber()
        quoteService = faker.friends()
        dateService = faker.date()
    }

    suspend fun getLastMessage(chatId: String): Message? {
        return withContext(Dispatchers.IO) {
            Random().nextInt(100).takeIf { it > 20 }?.let {
                val id = idService.valid()
                val date = dateService.past(70, DAYS)
                val text = quoteService.quote()
                Message(id, text, date.time)
            }
        }
    }
}