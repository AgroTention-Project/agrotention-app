package com.bangkit.agrotentionapp.view.main.fragment

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkit.agrotentionapp.R
import com.bangkit.agrotentionapp.databinding.FragmentProfileBinding
import com.bangkit.agrotentionapp.databinding.FragmentScanBinding
import com.bangkit.agrotentionapp.view.login.LoginActivity
import com.bangkit.agrotentionapp.view.main.model.ProfileViewModel
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlin.math.sign

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

        binding.btnLogout.setOnClickListener {
            signOut()
        }

    }

    private fun signOut() {
        // [START auth_sign_out]
        Firebase.auth.signOut()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }, 1000)

        // [END auth_sign_out]
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