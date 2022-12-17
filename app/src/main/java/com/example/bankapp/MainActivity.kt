package com.example.bankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.bankapp.databinding.ActivityMainBinding
import java.lang.Integer.max
import java.lang.Math.min


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object{
        var fragmentClicks = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        setupWithNavController(binding.bottomNavView,navController)
        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(1).isEnabled = false
        binding.bottomNavView.menu.getItem(0).setOnMenuItemClickListener { fragmentClicks = max(0,
            fragmentClicks-1); binding.add.setImageResource(R.drawable.ic_customer); false}
        binding.bottomNavView.menu.getItem(2).setOnMenuItemClickListener { fragmentClicks = min(1,
            fragmentClicks+1) ; binding.add.setImageResource(R.drawable.ic_add_money); false}
        binding.add.setOnClickListener {
            if(fragmentClicks == 0){
                startActivity(
                    Intent(this,AddCustomerActivity::class.java)
                )
            }else{
                startActivity(
                    Intent(this,MakeTransferActivity::class.java)
                )
            }
        }
    }

}