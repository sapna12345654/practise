package com.example.pratiseandroid
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var etusername : EditText
    lateinit var etemail : EditText
    lateinit var etpassword : EditText
    lateinit var btnCheck : Button

    lateinit var tvEmail : TextView
    lateinit var tvPassword: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        username = findViewById(R.id.etUsername)
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        btnCheck = findViewById(R.id.btnCheck)
        btnCheck.setOnClickListener {
            if(username.text.isEmpty()){//true
                username.error = "Enter Your Username"
            } else if(email.text.isEmpty()){
                email.error = "Enter Your Email"
            else if(password.text.isEmpty()){
                password.error = "Enter Your Password"
            }else{
                    tvEmail.text = email.text.toString()
                    tvPassword.text = password.text.toString()
                }
                    Toast.makeText(this, "Sign Up Successfully!", Toast.LENGTH_SHORT).show()


                }
        }
    }
}
