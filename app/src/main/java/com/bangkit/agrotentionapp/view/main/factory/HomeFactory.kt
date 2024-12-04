package com.bangkit.agrotentionapp.view.main.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.agrotentionapp.data.remote.NewsRepository
import com.bangkit.agrotentionapp.di.Injection
import com.bangkit.agrotentionapp.view.main.model.HomeViewModel

class HomeFactory private constructor(private val newsRepository: NewsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: HomeFactory? = null
        fun getInstance(context: Context): HomeFactory =
            instance ?: synchronized(this) {
                instance ?: HomeFactory(Injection.provideNewsRepository(context))
            }.also { instance = it }
    }

}