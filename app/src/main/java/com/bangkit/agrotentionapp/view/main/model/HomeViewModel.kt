package com.bangkit.agrotentionapp.view.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.agrotentionapp.data.remote.response.DataItem

class HomeViewModel : ViewModel() {

    private val _listNews = MutableLiveData<List<DataItem>>()
    val listEventUpcoming: LiveData<List<DataItem>> = _listNews

}