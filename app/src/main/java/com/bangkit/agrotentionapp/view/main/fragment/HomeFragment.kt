package com.bangkit.agrotentionapp.view.main.fragment

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.agrotentionapp.R
import com.bangkit.agrotentionapp.data.remote.response.DataItem
import com.bangkit.agrotentionapp.databinding.FragmentHomeBinding
import com.bangkit.agrotentionapp.databinding.FragmentScanBinding
import com.bangkit.agrotentionapp.view.adapter.NewsAdapter
import com.bangkit.agrotentionapp.view.main.factory.HomeFactory
import com.bangkit.agrotentionapp.view.main.model.HomeViewModel

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val homeModel: HomeViewModel by viewModels {
        HomeFactory.getInstance(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }

        homeModel.listNews.observe(viewLifecycleOwner) { news ->
            setNewsData(news)
        }

        homeModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }

        homeModel.errorMessage.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {errorMessage ->
                Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setNewsData(listNews: List<DataItem>) {
        val adapter = NewsAdapter(
            onItemClick = { link -> navigateToDetailNews(link) }
        )
        adapter.submitList(listNews)
        binding.rvNews.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun navigateToDetailNews(url: String) {

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)

    }

}