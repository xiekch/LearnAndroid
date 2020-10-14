package com.example.myapplication.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class MvvmViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private var numberLiveData: MutableLiveData<Int> = MutableLiveData()
    val userId: LiveData<Int> = liveData {
        val data = 0//database.loadUser() // loadUser is a suspend function.
        emit(data)
    }

    init {
        numberLiveData.value = 2
    }

    fun getNumber(): LiveData<Int> {
        return numberLiveData
    }

    fun add() {
        numberLiveData.value = (numberLiveData.value ?: 0) + 1
    }
}