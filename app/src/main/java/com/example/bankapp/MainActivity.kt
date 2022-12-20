package com.example.bankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.bankapp.customer.AddCustomerActivity
import com.example.bankapp.databinding.ActivityMainBinding
import com.example.bankapp.transfers.MakeTransferActivity
import java.lang.Integer.max
import java.lang.Math.min


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object{
        var currentFragment = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        setupWithNavController(binding.bottomNavView,navController)
        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(1).isEnabled = false
        binding.bottomNavView.menu.getItem(0).setOnMenuItemClickListener { currentFragment = max(0,
            currentFragment-1); binding.add.setImageResource(R.drawable.ic_customer); false}
        binding.bottomNavView.menu.getItem(2).setOnMenuItemClickListener { currentFragment = min(1,
            currentFragment+1) ; binding.add.setImageResource(R.drawable.ic_add_money); false}
        binding.add.setOnClickListener {
            if(currentFragment == 0){
                startActivity(
                    Intent(this, AddCustomerActivity::class.java)
                )
            }else{
                startActivity(
                    Intent(this, MakeTransferActivity::class.java)
                )
            }
        }
    }

}