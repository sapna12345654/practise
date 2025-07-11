package com.example.pratiseandroid.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pratiseandroid.R
import com.example.pratiseandroid.databinding.DialogDesignBinding
import com.example.pratiseandroid.databinding.DialogStudentBinding
import com.example.pratiseandroid.databinding.FragmentRecyclerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerFragment : Fragment(),  ClickInterface {
    lateinit var binding : FragmentRecyclerBinding
    lateinit var recyclerAdapter: RecyclerAdapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var studentList = ArrayList<StudentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentRecyclerBinding.inflate(layoutInflater)
       recyclerAdapter = RecyclerAdapter(studentList,this)
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.adapter = recyclerAdapter

        studentList.add(StudentModel(name = "Ankush", rollNo = "123","123412345"))
        studentList.add(StudentModel(name = "Vijay", rollNo = "123","123412345"))
        studentList.add(StudentModel(name = "Rajay", rollNo = "123","123412345"))
        studentList.add(StudentModel(name = "Sapna", rollNo = "123","123412345"))
        studentList.add(StudentModel(name = "Rudhar", rollNo = "123","123412345"))
        studentList.add(StudentModel(name = "Inderjot", rollNo = "123","123412345"))
        studentList.add(StudentModel(name = "Kuldeep", rollNo = "123","123412345"))

        binding.fabFavorite.setOnClickListener {
            updateDialog()
        }
        return binding.root
    }

    fun updateDialog(position : Int = -1){

        var dialog = Dialog(requireActivity())
        var dialogBinding = DialogStudentBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        5
        if(position != -1) {
            dialogBinding.etDialogName.setText(studentList[position].name)
            dialogBinding.etDialogRoll.setText(studentList[position].rollNo)
            dialogBinding.etDialogPhone.setText(studentList[position].contactNo)
        }


        dialog.show()
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        dialogBinding.btnDialogSave.setOnClickListener {
            if (dialogBinding.etDialogName.text.toString().isEmpty()) {
                dialogBinding.etDialogName.error = "Enter Your Name"
            } else if (dialogBinding.etDialogRoll.text.toString().isEmpty()) {
                dialogBinding.etDialogRoll.error = "Enter Your ROll NO"
            } else if (dialogBinding.etDialogPhone.text.toString().isEmpty()) {
                dialogBinding.etDialogPhone.error = "Enter Your Contact NO"
            } else {
                if (position == -1) {


                    studentList.add(
                        StudentModel(
                            name = dialogBinding.etDialogName.text.toString(),
                            rollNo = dialogBinding.etDialogRoll.text.toString(),
                            contactNo = dialogBinding.etDialogPhone.text.toString()
                        )
                    )
                    recyclerAdapter.notifyDataSetChanged()
                    dialog.dismiss()

                }
                else{

//                    studentList.set(position, StudentModel(
//                        name = dialogBinding.etName.text.toString(),
//                        rollNo = dialogBinding.etRollNo.text.toString(),
//                        contactNo = dialogBinding.etContact.text.toString()
//                    ))

                    studentList[position] = StudentModel(
                        name = dialogBinding.etDialogName.text.toString(),
                        rollNo = dialogBinding.etDialogRoll.text.toString(),
                        contactNo = dialogBinding.etDialogPhone.text.toString()
                    )
                    recyclerAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecyclerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun update(position: Int) {
        updateDialog(position)

        Toast.makeText(context, "${studentList[position].name}", Toast.LENGTH_SHORT).show()


    }

    override fun delete(position: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete")
            .setMessage("You want to Delete it")
            .setPositiveButton("Delete"){_,_->
                studentList.removeAt(position)
                recyclerAdapter.notifyDataSetChanged()

            }
            .setNegativeButton("Cancel"){_,_->

            }
            .show()
    }
}