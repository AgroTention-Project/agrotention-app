package com.bangkit.agrotentionapp.view.main.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.bangkit.agrotentionapp.databinding.FragmentScanBinding
import com.bangkit.agrotentionapp.util.getImageUri
import com.bangkit.agrotentionapp.view.ResponseActivity

class ScanFragment : Fragment() {

    // Deklarasi binding
    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private var currentImageUri: Uri? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRice.setOnClickListener {
            startCamera()
        }

        binding.btnTomato.setOnClickListener{
            startCamera()
        }

        binding.btnPotato.setOnClickListener{
            startCamera()
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri!!)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            shareImage()
        } else {
            currentImageUri = null
        }
    }

    private fun shareImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            navigateToResponse(it)
        }
    }


    private fun navigateToResponse(photo: Uri) {
        val intent = Intent(requireContext(), ResponseActivity::class.java).apply {
            putExtra("PHOTO", photo)
        }
        startActivity(intent)
    }


}