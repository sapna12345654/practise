package com.example.pratiseandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TroubleLoggingIn : AppCompatActivity() {
    lateinit var etUsername : EditText
    lateinit var btnCheck : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trouble_logging_in)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etUsername = findViewById(R.id.etUsername)
        btnCheck = findViewById(R.id.btnCheck)

        btnCheck.setOnClickListener {
            if(etUsername.text.isEmpty()){//true
                etUsername.error = "Enter Your Username or email"}
                else{
                    val intent= Intent(this, VerifyOtpActivity::class.java)
                    startActivity(intent)

                    this.finish()
                }
    }}}