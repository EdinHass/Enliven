package com.example.enliven.ui.chat

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.enliven.data.ImageURLS
import com.example.enliven.data.UserExtra
import com.example.enliven.databinding.ItemUserBinding
import com.example.enliven.databinding.ItemUserLeaderboardsBinding
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

class LeaderboardsAdapter: BaseListAdapter<User, ItemUserLeaderboardsBinding>(Comparator()) {
    private var counter = 1

    override fun getItemViewBinding(parent: ViewGroup): ItemUserLeaderboardsBinding {
        return ItemUserLeaderboardsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bindItem(binding: ItemUserLeaderboardsBinding, item: User) {
        binding.textViewName.text = item.name
        if(item.extraData[UserExtra.XP]!=null) {
            binding.textViewPhone.text =
                "Trenutni XP: ${Math.round(item.extraData[UserExtra.XP] as Double).toString()}"
        }else{
            binding.textViewPhone.text = "Trenutni XP: 0"
        }
        binding.place.text = "#${counter}"
        when(counter++){
            1 ->  binding.place.setTextColor(Color.parseColor("#FFD700"))
            2 ->  binding.place.setTextColor(Color.parseColor("#C0C0C0"))
            3 ->  binding.place.setTextColor(Color.parseColor("#CD7F32"))
        }
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

class LeaderboardsStreaksAdapter: BaseListAdapter<User, ItemUserLeaderboardsBinding>(Comparator()) {
    private var counter = 1

    override fun getItemViewBinding(parent: ViewGroup): ItemUserLeaderboardsBinding {
        return ItemUserLeaderboardsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bindItem(binding: ItemUserLeaderboardsBinding, item: User) {
        binding.textViewName.text = item.name
        if(item.extraData[UserExtra.XP]!=null) {
            binding.textViewPhone.text =
                "Trenutni Streak: ${Math.round(item.extraData[UserExtra.STREAK] as Double).toString()}"
        }else{
            binding.textViewPhone.text = "Trenutni Streak: 0"
        }
        binding.place.text = "#${counter}"
        when(counter++){
            1 ->  binding.place.setTextColor(Color.parseColor("#FFD700"))
            2 ->  binding.place.setTextColor(Color.parseColor("#C0C0C0"))
            3 ->  binding.place.setTextColor(Color.parseColor("#CD7F32"))
        }
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