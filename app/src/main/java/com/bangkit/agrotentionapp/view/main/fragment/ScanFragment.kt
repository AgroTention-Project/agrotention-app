package com.bangkit.agrotentionapp.view.main.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkit.agrotentionapp.R
import com.bangkit.agrotentionapp.view.main.model.ScanViewModel

class ScanFragment : Fragment() {

    companion object {
        fun newInstance() = ScanFragment()
    }

    private val viewModel: ScanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_scan, container, false)
    }
}