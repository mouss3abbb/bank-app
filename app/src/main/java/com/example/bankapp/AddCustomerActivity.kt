package com.example.bankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil.setContentView
import com.example.bankapp.databinding.ActivityAddCustomerBinding

class AddCustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCustomerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = BankDB(this,null)
        binding.addCustomer.setOnClickListener {
            db.insertCustomer(Customer(binding.nameEt.text.toString(),binding.emailEt.text.toString(),binding.balanceEt.text.toString().toDouble()))
            startActivity(
                Intent(this,MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

    }
}