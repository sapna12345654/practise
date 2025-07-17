package com.example.pratiseandroid.practiseroomdb

import android.app.Dialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pratiseandroid.R
import com.example.pratiseandroid.databinding.ActivityDataBaseBinding
import com.example.pratiseandroid.databinding.DialogStudentBinding

class DataBaseActivity : AppCompatActivity() {
    lateinit var binding : ActivityDataBaseBinding
    lateinit var userDatabase : UserDatabase
   //adapter declaration
    lateinit var databaseAdapter: DatabaseAdapter
    var userList= ArrayList<UserModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDataBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userDatabase = UserDatabase.getDatabase(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //adapter initialization
        databaseAdapter = DatabaseAdapter(userList)
        binding.rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = databaseAdapter
        getUserData()

        binding.fabBtn.setOnClickListener {
            var dialog = Dialog(this)
            var dialogBinding = DialogStudentBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            dialogBinding.btnDialogSave.setOnClickListener {
                if (dialogBinding.etDialogName.text.toString().isEmpty()) {
                    dialogBinding.etDialogName.error = "Enter Name"
                } else if (dialogBinding.etDialogPhone.text.toString().isEmpty()) {
                    dialogBinding.etDialogPhone.error = "Enter Contact No"
                } else {
                    var userModel = UserModel(
                        userName = dialogBinding.etDialogName.text.toString(),
                        userContact = dialogBinding.etDialogPhone.text.toString()
                    )
                    userDatabase.userDao().insertUser(userModel)
                    getUserData()
                    dialog.dismiss()

                }
            }
            dialog.show()
        }

    }

     fun getUserData() {
        userList.clear()
        userList.addAll(userDatabase.userDao().getUserData())
        databaseAdapter.notifyDataSetChanged()
    }
    }
