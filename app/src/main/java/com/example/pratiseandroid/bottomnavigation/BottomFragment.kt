package com.example.pratiseandroid.bottomnavigation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.content.ContextCompat
import com.example.pratiseandroid.R
import com.example.pratiseandroid.databinding.FragmentBottomBinding


/**
 * A simple [Fragment] subclass.
 * Use the [BottomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomFragment : Fragment() {
    lateinit var binding: FragmentBottomBinding
    var imageUri: Uri?=null

    var galleryPermission=registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted->
     if(isGranted){
         Toast.makeText(requireContext(),"Permission Granted",Toast.LENGTH_SHORT).show()
     }else{
         Toast.makeText(requireContext(),"Permission Not Granted",Toast.LENGTH_SHORT).show()
     }}
    var cameraPermission=registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted->
        if(isGranted){
            Toast.makeText(requireContext(),"Permission Granted",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(),"Permission Not Granted",Toast.LENGTH_SHORT).show()
        }}
    var galleryPick=registerForActivityResult(ActivityResultContracts.GetContent()){uri->
        uri?.let {
            binding.ivImage.setImageURI(it)
        }}
    val takePicturePreview = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            binding.ivImage.setImageBitmap(it)
        } ?: run {
            Toast.makeText(requireContext(), "Failed to capture image", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     binding= FragmentBottomBinding.inflate(layoutInflater)
       // binding.ivImage.setImageURI(imageUri)
        binding.btnImage.setOnClickListener{
            if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                println("Check SelectedImage:$imageUri")

                galleryPick.launch("image/*")
            }else{
                galleryPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        binding.btnCamera.setOnClickListener{
            if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
             takePicturePreview.launch()
            }else{
                cameraPermission.launch(Manifest.permission.CAMERA)
            }
        }
    return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BottomFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BottomFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}

