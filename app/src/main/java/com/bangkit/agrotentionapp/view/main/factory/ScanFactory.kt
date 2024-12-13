package com.bangkit.agrotentionapp.view.main.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.agrotentionapp.data.remote.NewsRepository
import com.bangkit.agrotentionapp.di.Injection
import com.bangkit.agrotentionapp.view.main.model.HomeViewModel
import com.bangkit.agrotentionapp.view.main.model.ScanViewModel

class ScanFactory private constructor(private val newsRepository: NewsRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScanViewModel::class.java)) {
            return ScanViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ScanFactory? = null
        fun getInstance(context: Context): ScanFactory =
            instance ?: synchronized(this) {
                instance ?: ScanFactory(Injection.provideNewsRepository(context))
            }.also { instance = it }
    }

}