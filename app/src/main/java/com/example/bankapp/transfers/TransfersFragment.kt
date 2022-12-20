package com.example.bankapp.transfers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.bankapp.R
import com.example.bankapp.databinding.FragmentTransfersBinding
import com.example.bankapp.db.BankDB


class TransfersFragment : Fragment() {
    private lateinit var binding: FragmentTransfersBinding
    private lateinit var db: BankDB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transfers,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = BankDB(requireContext(),null)
        val cursor = db.getAllTransfers()
        val adapterList = mutableListOf<Transfer>()
        if(cursor != null) {
            while (cursor.moveToNext()) {
                adapterList.add(
                    Transfer(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3).toDouble()
                    )
                )
            }
            cursor.close()
        }
        binding.transfersRv.adapter = TransfersAdapter(adapterList.reversed())
    }
}