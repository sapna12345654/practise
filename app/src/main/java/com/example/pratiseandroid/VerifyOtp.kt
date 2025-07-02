package com.example.pratiseandroid

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerifyOtpActivity : AppCompatActivity() {

    private lateinit var otp1: EditText
    private lateinit var otp2: EditText
    private lateinit var otp3: EditText
    private lateinit var otp4: EditText
    private lateinit var btnVerify: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)

        otp1 = findViewById(R.id.otp1)
        otp2 = findViewById(R.id.otp2)
        otp3 = findViewById(R.id.otp3)
        otp4 = findViewById(R.id.otp4)
        btnVerify = findViewById(R.id.btnVerify)

        setupOtpField(otp1, otp2)
        setupOtpField(otp2, otp3)
        setupOtpField(otp3, otp4)
        setupOtpField(otp4, null)

        btnVerify.setOnClickListener {
            if (listOf(otp1, otp2, otp3, otp4).any { it.text.isBlank() }) {
                Toast.makeText(this, "Please enter all 4 digits", Toast.LENGTH_SHORT).show()
            } else {
                val otp = otp1.text.toString() +
                        otp2.text.toString() +
                        otp3.text.toString() +
                        otp4.text.toString()
                Toast.makeText(this, "OTP entered: $otp", Toast.LENGTH_SHORT).show()
                // TODO: perform OTP verification
                finish()
            }
        }
    }

    private fun setupOtpField(current: EditText, next: EditText?) {
        current.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.length == 1) {
                    next?.requestFocus()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        current.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if ((keyCode == KeyEvent.KEYCODE_DEL) && current.text.isEmpty()) {
                next?.let {
                    // no previous context here, so auto focus skipped
                }
                // try shifting focus backward
                (v.focusSearch(View.FOCUS_LEFT) as? EditText)?.requestFocus()
            }
            false

        })
    }
}
