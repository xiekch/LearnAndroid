package com.example.myapplication.library

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MvvmViewModel : ViewModel() {
    val userLiveData = MutableLiveData<Person>()
    val person = Person("Tom", "Gree", 10)

    fun changeUser() {
        userLiveData.value = person
    }
}