package com.tkitm.recyclerviewdelegate.sample.presentation.presentor

sealed interface ChatsEvent {
    data class OpenChat(val id: String)
}