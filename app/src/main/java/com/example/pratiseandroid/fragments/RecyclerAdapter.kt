package com.example.pratiseandroid.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pratiseandroid.R
import com.example.pratiseandroid.databinding.BaseViewBinding

class RecyclerAdapter(var studentList : ArrayList<StudentModel>, var clickInterface: ClickInterface) : RecyclerView.Adapter<RecyclerAdapter.ViewAdapter>() {
    //    class ViewAdapter(var binding: BaseViewBinding): RecyclerView.ViewHolder(binding.root) {
    class ViewAdapter(var view : View): RecyclerView.ViewHolder(view) {
        //val btnEdit: View = view.findViewById(R.id.btnEdit)
      //  val btnDelete: View = view.findViewById(R.id.btnDelete)

//        fun bind(position: Int, student : ArrayList<StudentModel>) {
//            binding.tvName.text = student[position].name
//            binding.tvRollNo.text = student[position].rollNo
//            binding.tvContact.text = student[position].contactNo
//
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAdapter {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.base_view,parent,false)

        return  ViewAdapter(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: ViewAdapter, position: Int) {
        // holder.bind(position, studentList)
        var name = holder.view.findViewById<TextView>(R.id.tvName)
        var rollNo = holder.view.findViewById<TextView>(R.id.tvRollNo)
        var contact = holder.view.findViewById<TextView>(R.id.tvContact)
        var edit=holder.view.findViewById<ImageView>(R.id.btnEdit)
        var delete=holder.view.findViewById<ImageView>(R.id.btnDelete)

        name.text = studentList[position].name
        rollNo.text = studentList[position].rollNo
        contact.text = studentList[position].contactNo
//for long press delete or item click update
        holder.itemView.setOnClickListener {
            clickInterface.update(position)

        }
       holder.itemView.setOnLongClickListener {
            clickInterface.delete(position)

           return@setOnLongClickListener true

       }
        edit.setOnClickListener {
            clickInterface.update(position)
       }

        delete.setOnClickListener {
            clickInterface.delete(position)
       }


    }
}