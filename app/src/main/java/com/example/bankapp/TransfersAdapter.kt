package com.example.bankapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransfersAdapter(private val dataset: List<Transfer>): RecyclerView.Adapter<TransfersAdapter.TransferViewHolder>(){

    class TransferViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val from: TextView = itemView.findViewById<TextView>(R.id.from)
        val to: TextView = itemView.findViewById<TextView>(R.id.to)
        val amount: TextView = itemView.findViewById<TextView>(R.id.balance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transfer_layout,parent,false)
        return TransferViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TransferViewHolder, position: Int) {
        holder.from.text = dataset[position].sender
        holder.to.text = dataset[position].receiver
        holder.amount.text = holder.amount.text.toString() +  dataset[position].amount.toString()
    }

    override fun getItemCount(): Int = dataset.size
}