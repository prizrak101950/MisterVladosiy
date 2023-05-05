package com.example.razv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class DataModel:ViewModel() {
    val level:MutableLiveData<String> by lazy{ MutableLiveData<String>() }
    val word:MutableLiveData<String> by lazy { MutableLiveData<String>()
    }
}