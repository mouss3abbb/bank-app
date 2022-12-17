package com.example.bankapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.databinding.ActivityMainBinding
import com.example.bankapp.databinding.FragmentCustomersBinding


class CustomersFragment : Fragment() {
    private lateinit var binding: FragmentCustomersBinding
    private lateinit var db: BankDB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_customers,container,false)
        return binding.root
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = BankDB(requireContext(),null)
        val cursor = db.getAllCustomers()
        val adapterList = mutableListOf<Customer>()
        if(cursor != null) {
            while (cursor.moveToNext()) {
                adapterList.add(
                    Customer(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2).toDouble()
                    )
                )
            }
            cursor.close()
        }
        binding.customersRv.adapter = CustomersAdapter(adapterList.reversed())
    }
}