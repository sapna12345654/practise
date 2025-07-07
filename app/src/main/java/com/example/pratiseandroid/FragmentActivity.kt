package com.example.pratiseandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pratiseandroid.databinding.ActivityFragmentBinding
import com.example.pratiseandroid.interaction.ClickInterface

class FragmentActivity : AppCompatActivity() {
    lateinit var binding : ActivityFragmentBinding
    lateinit var clickInterface: ClickInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        binding.btnClick.setOnClickListener {

            clickInterface.changeColor(1)
        }
        binding.btnGreen.setOnClickListener {

            clickInterface.changeColor(2)
        }
        binding.btnBlue.setOnClickListener {

            clickInterface.changeColor(3)
        }
        binding.btnPurple.setOnClickListener {

            clickInterface.changeColor(4)
        }
    }


}