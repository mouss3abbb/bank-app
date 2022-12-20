package com.example.bankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bankapp.databinding.ActivityMakeTransferBinding

class MakeTransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakeTransferBinding
    private lateinit var db: BankDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = BankDB(this,null)
        binding.addTransfer.setOnClickListener {
            db.makeTransfer(
                Transfer(
                    binding.senderEt.text.toString().trim(),
                    binding.receiverEt.text.toString().trim(),
                    binding.amountEt.text.toString().trim().toDouble()
                )
            )
            startActivity(
                Intent(this,MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }
}