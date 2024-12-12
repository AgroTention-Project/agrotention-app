package com.bangkit.agrotentionapp.view.main.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkit.agrotentionapp.R
import com.bangkit.agrotentionapp.databinding.FragmentProfileBinding
import com.bangkit.agrotentionapp.databinding.FragmentScanBinding
import com.bangkit.agrotentionapp.view.main.model.ProfileViewModel
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser

        val name = user?.displayName
        val email = user?.email
        val photoUrl = user?.photoUrl
        val phoneNumber = user?.phoneNumber ?: "-"
        
        binding.nameProfile.text = name
        binding.emailProfile.text = email
        binding.tlpProfile.text = phoneNumber

        Glide.with(binding.root)
            .load(photoUrl)
            .into(binding.imageView)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Menghindari memory leak
    }

}