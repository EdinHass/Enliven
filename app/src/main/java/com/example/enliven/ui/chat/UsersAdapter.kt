package com.example.enliven.ui.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.enliven.data.UserExtra
import com.example.enliven.databinding.ItemUserBinding
import com.example.enliven.ui.base.BaseListAdapter
import io.getstream.chat.android.client.models.User

class UsersAdapter: BaseListAdapter<User, ItemUserBinding>(Comparator()) {

    override fun getItemViewBinding(parent: ViewGroup): ItemUserBinding {
        return ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bindItem(binding: ItemUserBinding, item: User) {
        binding.textViewName.text = item.name
        Log.e("Hey", "Bound ${item.extraData[UserExtra.PHONE].toString()}")
        binding.textViewPhone.text = item.extraData[UserExtra.PHONE].toString()
    }


    class Comparator: DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

    }
}