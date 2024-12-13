package com.bangkit.agrotentionapp.view.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.agrotentionapp.data.remote.NewsRepository
import com.bangkit.agrotentionapp.data.remote.response.DataItem
import com.bangkit.agrotentionapp.data.remote.Result
import com.bangkit.agrotentionapp.util.EventWrapper

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _listNews = MutableLiveData<List<DataItem>>()
    val listNews: LiveData<List<DataItem>> = _listNews

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<EventWrapper<String>>()
    val errorMessage: LiveData<EventWrapper<String>> = _errorMessage


    init {
        fetchNews()
    }

    private fun fetchNews() {
        _isLoading.value = true
        newsRepository.fetchNews().observeForever { result ->
            when(result) {
                is Result.Loading -> _isLoading.value = true
                is Result.Success -> {
                    _isLoading.value = false
                    _listNews.value = result.data
                }

                is Result.Error -> {
                    _isLoading.value = false
                    _errorMessage.value = EventWrapper("Error: ${result.error}")
                }

            }
        }

    }

    companion object {
        private const val TAG = "HomeModel"
    }

}