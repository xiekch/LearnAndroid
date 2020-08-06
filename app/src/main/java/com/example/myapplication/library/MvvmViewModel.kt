package com.example.myapplication.library

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MvvmViewModel : ViewModel() {
    private lateinit var numberLiveData: MutableLiveData<Int>

    fun getNumber(): LiveData<Int> {
        if (!::numberLiveData.isInitialized) {
            numberLiveData = MutableLiveData()
            numberLiveData.value = 0
        }
        return numberLiveData
    }

    fun add() {
        numberLiveData.value = (numberLiveData.value ?: 0) + 1
    }
}