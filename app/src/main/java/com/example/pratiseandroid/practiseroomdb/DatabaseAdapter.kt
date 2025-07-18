package com.example.pratiseandroid.practiseroomdb

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.pratiseandroid.databinding.PracticeViewBinding

data class DatabaseAdapter(var userList: ArrayList<UserModel>,var clickInterface: ClickInterface): RecyclerView.Adapter<DatabaseAdapter.ViewHolder>(){
    class ViewHolder(var binding: PracticeViewBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup,viewType:Int): ViewHolder{
        var binding= PracticeViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
      holder.binding.tvName.text=userList[position].userName
        holder.binding.tvContact.text=userList[position].userContact

        holder.itemView.setOnClickListener {
            clickInterface.updateUser(position)
        }
        holder.itemView.setOnLongClickListener {
            clickInterface.deleteUser(position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
      return userList.size
    }


}
