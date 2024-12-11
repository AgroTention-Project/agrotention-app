package com.bangkit.agrotentionapp.view.main.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bangkit.agrotentionapp.databinding.FragmentScanBinding
import com.bangkit.agrotentionapp.util.getImageUri
import com.bangkit.agrotentionapp.view.CameraActivity
import com.bangkit.agrotentionapp.view.CameraActivity.Companion.CAMERAX_RESULT
import com.bangkit.agrotentionapp.view.main.model.ScanViewModel

class ScanFragment : Fragment() {

    // Deklarasi binding
    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private var currentImageUri: Uri? = null


    //start camera x
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireActivity(), "Permission request granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "Permission request denied", Toast.LENGTH_SHORT).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireActivity(),
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED


    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }

    //end

//    companion object {
//        fun newInstance() = ScanFragment()
//    }

    private val viewModel: ScanViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRice.setOnClickListener {
            //camera x
            startCameraX()
        }

        //start
        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }
        //end

        binding.btnTomato.setOnClickListener{
            startCamera()
        }

        binding.btnPotato.setOnClickListener{
            startCamera()
        }

    }

    //start camera x
    private fun startCameraX() {
        val intent = Intent(requireContext(), CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERAX_RESULT) {
            currentImageUri = it.data?.getStringExtra(CameraActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
        }
    }

    //end


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
        // showImage()
        } else {
            currentImageUri = null
        }
    }

}