package com.example.myapplication.component

interface DownloadListener {
    fun onProgress(progress:Int)
    fun onSuccess()
    fun onFailed()
    fun onPaused()
    fun onCanceled()
}