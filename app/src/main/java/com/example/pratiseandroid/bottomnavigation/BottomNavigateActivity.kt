package com.example.pratiseandroid.bottomnavigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.pratiseandroid.R
import com.example.pratiseandroid.databinding.ActivityBottomNavigate1Binding

class BottomNavigateActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavigate1Binding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityBottomNavigate1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navController=findNavController(R.id.nav)
        binding.bottomNav.setOnItemSelectedListener{it->
            when(it.itemId){
                R.id.itemHome->{ navController.navigate(R.id.bottomFragment)}
                R.id.itemSetting->{ navController.navigate(R.id.settingFragment)
            }
        }
        return@setOnItemSelectedListener true
    }
}}