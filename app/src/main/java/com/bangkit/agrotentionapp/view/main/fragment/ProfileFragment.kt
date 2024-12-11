package com.bangkit.agrotentionapp.view.main.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkit.agrotentionapp.R
import com.bangkit.agrotentionapp.databinding.FragmentProfileBinding
import com.bangkit.agrotentionapp.view.main.model.ProfileViewModel
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {


    companion object {
        fun newInstance() = ProfileFragment()
    }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val user = FirebaseAuth.getInstance().currentUser

        val name = user?.displayName
        val email = user?.email
        val photoUrl = user?.photoUrl


        binding.nameProfile.text = name
        binding.emailProfile.text = email
        Glide.with(binding.root)
            .load(photoUrl)
            .into(binding.imageView)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}