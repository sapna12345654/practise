package com.example.pratiseandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pratiseandroid.databinding.ActivityImplicitBinding

class ImplicitActivity : AppCompatActivity() {
    lateinit var binding: ActivityImplicitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityImplicitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnWebsite.setOnClickListener{
            var url= Uri.parse("https://developer.android.com/studio")
            val intent= Intent(Intent.ACTION_VIEW,url)
            startActivity(intent)

        }
    }
}