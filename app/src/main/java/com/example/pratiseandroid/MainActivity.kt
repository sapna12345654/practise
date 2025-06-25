package com.example.pratiseandroid
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import android.widget.CheckBox
class MainActivity : AppCompatActivity() {
    lateinit var etUsername : EditText
    lateinit var etEmail : EditText
    lateinit var etPassword:EditText
    lateinit var btnCheck : Button
    lateinit var cbTerms: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        btnCheck = findViewById(R.id.btnCheck)
        cbTerms = findViewById(R.id.cbTerms)



        btnCheck.setOnClickListener {
            if(etUsername.text.isEmpty()){//true
                etUsername.error = "Enter Your Username"
            }else if(etEmail.text.isEmpty()){
                etEmail.error = "Enter Your Email"
            }else if(etPassword.text.isEmpty()) {
                etPassword.error = "Enter Your Password"
            }else if (!cbTerms.isChecked) {
                Toast.makeText(this, "Please accept Terms and Privacy Policy", Toast.LENGTH_SHORT)
                    .show()
            }else{
                println("Username: ${etUsername.text}")
                println(" Email: ${etEmail.text}")
                println(" Password: ${etPassword.text}")

                Toast.makeText(this, "Sign up successfully", Toast.LENGTH_SHORT).show()



            }}
        }}