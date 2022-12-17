package com.example.bankapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomersAdapter(private val dataset: List<Customer>) : RecyclerView.Adapter<CustomersAdapter.CustomersViewHolder>(){

    class CustomersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTv: TextView
        val emailTv: TextView
        val balanceTv: TextView
        val id: TextView
        init {
            nameTv = itemView.findViewById(R.id.name)
            emailTv = itemView.findViewById(R.id.email)
            balanceTv = itemView.findViewById(R.id.balance)
            id = itemView.findViewById(R.id.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.customer_layout,parent,false)
        return CustomersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomersViewHolder, position: Int) {
        holder.nameTv.text = dataset[position].name
        holder.emailTv.text = dataset[position].email
        holder.balanceTv.text = dataset[position].currentBalance.toString()
    }


    override fun getItemCount(): Int = dataset.size

}