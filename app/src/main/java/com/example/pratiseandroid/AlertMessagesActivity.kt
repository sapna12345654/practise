package com.example.pratiseandroid
import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
binding.btnDialog.setOnClickListener{
    var dialog = Dialog(this)
    dialog.setContentView(R.layout.dialog_design)
    dialog.window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)//
    var name = dialog.findViewById<EditText>(R.id.etName)
    var save = dialog.findViewById<Button>(R.id.btnSave)
    val currentText = binding.tvUserName.text.toString()
    name.setText(currentText)
    save.setOnClickListener {
        if(name.text.isEmpty()){
            name.error = "Enter Your Name"
        }else{
binding.tvUserName.text= name.text.toString()
            dialog.dismiss()

        }
    }
    dialog.show()
}
    }
}




