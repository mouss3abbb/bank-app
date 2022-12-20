package com.example.bankapp.customer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bankapp.MainActivity
import com.example.bankapp.databinding.ActivityAddCustomerBinding
import com.example.bankapp.db.BankDB

class AddCustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCustomerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = BankDB(this,null)
        binding.addCustomer.setOnClickListener {
            db.insertCustomer(Customer(binding.nameEt.text.toString().trim(),binding.emailEt.text.toString().trim(),binding.balanceEt.text.toString().trim().toDouble()))
            Toast.makeText(this,"Customer added successfully",Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(this, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

    }
}