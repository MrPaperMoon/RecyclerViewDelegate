package com.tkitm.recyclerviewdelegate.sample.data.repository

import com.github.javafaker.*
import com.tkitm.recyclerviewdelegate.sample.domain.entity.User
import kotlinx.coroutines.*

class UserRepository {

    private val idService: IdNumber
    private val nameService: Name
    private val avatarService: Avatar

    init {
        val faker = Faker()
        idService = faker.idNumber()
        nameService = faker.name()
        avatarService = faker.avatar()
    }

    suspend fun getUserList(): List<User> {
        return withContext(Dispatchers.IO) {
            (0 until 20).map {
                val id = idService.valid()
                val username = nameService.username()
                val avatar = avatarService.image()
                User(id, username, avatar)
            }
        }
    }
}