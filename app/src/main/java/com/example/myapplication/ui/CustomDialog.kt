package com.example.myapplication.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R

class CustomDialog(context: Context) : AlertDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_custom)
    }
}