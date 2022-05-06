package com.example.handleronline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handleronline.databinding.ItemUserBinding
import com.example.handleronline.entity.User

class UserAdapter(var list : List<User>) : RecyclerView.Adapter<UserAdapter.VH>() {

    inner class VH(var itemUserBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemUserBinding.root){
        fun onBind(user: User){
            itemUserBinding.tvUsername.text = user.username
            itemUserBinding.tvUserPassword.text = user.password
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}