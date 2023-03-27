package com.tkitm.recyclerviewdelegate.sample.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.tkitm.recyclerviewdelegate.sample.R
import com.tkitm.recyclerviewdelegate.sample.presentation.presentor.ChatsViewModel

class ChatsFragment : Fragment(R.layout.chats) {

    private val viewModel: ChatsViewModel by viewModels()

    private val chatsAdapter by lazy { RecyclerViewAdapter(viewModel) }

    override fun onStart() {
        super.onStart()

        viewModel.chatListFlow.observe(this) {
            chatsAdapter.updateList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chats = view.findViewById<RecyclerView>(R.id.chats_list_view)
        val refresh = view.findViewById<ExtendedFloatingActionButton>(R.id.chats_refresh)

        chats.adapter = chatsAdapter
        refresh.setOnClickListener {
            viewModel.refreshData()
        }
    }
}