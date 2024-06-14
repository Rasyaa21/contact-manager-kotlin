package com.rasya.contact_manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val contact: ArrayList<UserData>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvPhoneNum: TextView = itemView.findViewById(R.id.tvPhoneNum)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contact[position]
        holder.tvName.text =  "Name : ${contact.name.toString()}"
        holder.tvPhoneNum.text = "Number : ${contact.phoneNumber.toString()}"
        holder.tvEmail.text = "Email : ${contact.email.toString()}"
    }

    override fun getItemCount(): Int {
        return contact.size
    }
}