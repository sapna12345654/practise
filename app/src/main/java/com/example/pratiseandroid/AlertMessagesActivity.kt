package com.example.pratiseandroid
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pratiseandroid.databinding.ActivityAlertMessagesBinding
import com.google.android.material.snackbar.Snackbar



class AlertMessagesActivity : AppCompatActivity() {
    lateinit var binding : ActivityAlertMessagesBinding
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAlertMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvInteger.text = counter.toString()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnToast.setOnClickListener {
            Toast.makeText(this,"This is Alert Message",Toast.LENGTH_LONG).show()
        }

        binding.btnSnack.setOnClickListener{
            Snackbar.make(binding.btnSnack,"I'm SnackBar", Snackbar.LENGTH_INDEFINITE)
                .setAction("ok"){
                }.show()
        }
        binding.btnAlert.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Alert!")
                .setMessage("Hello I'm Danger")
                .setPositiveButton("Add"){_,_->
                    counter++
                    binding.tvInteger.text = counter.toString()

                }
                .setNegativeButton("Sub"){_,_->
                    counter--
                    binding.tvInteger.text = counter.toString()

                }
                .setNeutralButton("zero"){_,_->
                    counter = 0
                    binding.tvInteger.text = counter.toString()
                }

                .show()

        }
    }
}