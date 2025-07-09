package com.example.pratiseandroid.fragments
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.pratiseandroid.R
import com.example.pratiseandroid.databinding.FragmentBaseBinding

class BaseFragment : Fragment() {

    private lateinit var binding: FragmentBaseBinding
    private lateinit var listAdapter: ListBaseAdapter
    private val studentList = ArrayList<StudentModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseBinding.inflate(inflater, container, false)

        // Add sample data
        studentList.add(StudentModel("Sapna", "12345", "1234567890"))
        studentList.add(StudentModel("Rudhar", "2121", "1234512345"))

        // Adapter with edit callback
        listAdapter = ListBaseAdapter(studentList) { position ->
            showStudentDialog(isEdit = true, editPosition = position)
        }
        binding.lvList.setOnItemClickListener { _, _, position, _ ->
            showStudentDialog(isEdit = true, editPosition = position)
        }
        binding.lvList.adapter = listAdapter

        // Add new student
        binding.fabFavorite.setOnClickListener {
            showStudentDialog(isEdit = false)
        }

        // Delete on long press
        binding.lvList.setOnItemLongClickListener { _, _, position, _ ->
            studentList.removeAt(position)
            listAdapter.notifyDataSetChanged()
            Toast.makeText(context, "Student deleted", Toast.LENGTH_SHORT).show()
            true
        }

        return binding.root
    }

    private fun showStudentDialog(isEdit: Boolean, editPosition: Int = -1) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_student)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val etName = dialog.findViewById<EditText>(R.id.etDialogName)
        val etRoll = dialog.findViewById<EditText>(R.id.etDialogRoll)
        val etPhone = dialog.findViewById<EditText>(R.id.etDialogPhone)
        val btnSave = dialog.findViewById<Button>(R.id.btnDialogSave)

        if (isEdit && editPosition >= 0) {
            val student = studentList[editPosition]
            etName.setText(student.name)
            etRoll.setText(student.rollNo)
            etPhone.setText(student.contactNo)
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val roll = etRoll.text.toString().trim()
            val phone = etPhone.text.toString().trim()

            if (name.isEmpty() || roll.isEmpty() || phone.isEmpty()) {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isEdit && editPosition >= 0) {
                studentList[editPosition] = StudentModel(name, roll, phone)
            } else {
                studentList.add(StudentModel(name, roll, phone))
            }

            listAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }

        dialog.show()
    }
}