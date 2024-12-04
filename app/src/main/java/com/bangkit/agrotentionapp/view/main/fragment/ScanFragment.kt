package com.bangkit.agrotentionapp.view.main.fragment

import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.bangkit.agrotentionapp.databinding.FragmentScanBinding
import com.bangkit.agrotentionapp.util.getImageUri
import com.bangkit.agrotentionapp.view.main.model.ScanViewModel

class ScanFragment : Fragment() {

    // Deklarasi binding
    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private var currentImageUri: Uri? = null


    companion object {
        fun newInstance() = ScanFragment()
    }

    private val viewModel: ScanViewModel by viewModels()

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
        // showImage()
        } else {
            currentImageUri = null
        }
    }



}