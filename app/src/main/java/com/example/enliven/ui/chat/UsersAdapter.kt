package com.example.enliven.ui.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.enliven.data.ImageURLS
import com.example.enliven.data.UserExtra
import com.example.enliven.databinding.ItemUserBinding
import com.example.enliven.ui.base.BaseListAdapter
import com.squareup.picasso.Picasso
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

        binding.textViewPhone.text = item.extraData[UserExtra.PHONE].toString()
        if(item.image == null) {
            Picasso.get().load(ImageURLS.DEFAULT).into(binding.userpicture);
        }
        else{
            Picasso.get().load(item.image as String?).into(binding.userpicture);
        }
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