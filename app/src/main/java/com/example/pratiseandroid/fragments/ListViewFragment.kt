package com.example.pratiseandroid.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.pratiseandroid.databinding.DialogDesignBinding
import com.example.pratiseandroid.databinding.FragmentListViewBinding

class ListViewFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentListViewBinding
    private lateinit var arrayAdapter: ArrayAdapter<String>

    private var list = arrayListOf("Inderjot", "Sapna", "Kulpdeep", "Rudhar", "Inderjot", "Inderjot", "Sapna", "Kulpdeep", "Rudhar")

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
    ): View {
        binding = FragmentListViewBinding.inflate(inflater, container, false)

        arrayAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, list)
        binding.lvList.adapter = arrayAdapter

        // Edit item on click
        binding.lvList.setOnItemClickListener { _, _, position, _ ->
            showNameDialog(isEdit = true, editPosition = position)
        }

        // Delete item on long click
        binding.lvList.setOnItemLongClickListener { _, _, position, _ ->
            list.removeAt(position)
            arrayAdapter.notifyDataSetChanged()
            true
        }

        // Add new item with FAB
        binding.fabBtn.setOnClickListener {
            showNameDialog(isEdit = false)
        }

        return binding.root
    }

    private fun showNameDialog(isEdit: Boolean, editPosition: Int = -1) {
        val dialog = Dialog(requireActivity())
        val dialogBinding = DialogDesignBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialogBinding.btnSave.text = if (isEdit) "Update" else "Save"

        if (isEdit && editPosition != -1) {
            dialogBinding.etName.setText(list[editPosition])
        }

        dialogBinding.btnSave.setOnClickListener {
            val name = dialogBinding.etName.text.toString().trim()
            if (name.isEmpty()) {
                dialogBinding.etName.error = "Enter Name"
            } else {
                if (isEdit && editPosition != -1) {
                    list[editPosition] = name
                } else {
                    list.add(name)
                }
                arrayAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
