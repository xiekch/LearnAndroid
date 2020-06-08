package com.example.myapplication.storage

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class FilesActivity : AppCompatActivity(), View.OnClickListener {
    private val REQUEST_PERMISSION = 1;
    private lateinit var buttonRequestPermissions: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_files)

        buttonRequestPermissions = findViewById(R.id.button_request_permissions)
        buttonRequestPermissions.setOnClickListener(this)
    }


    private fun checkPermissionAllGranted(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION) {
            Toast.makeText(this, "permissions accepted", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestStorage() {
        val mPermissionsArrays = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (checkPermissionAllGranted(mPermissionsArrays)) {
            Toast.makeText(this, "permissions accepted", Toast.LENGTH_LONG).show()
        } else {
            requestPermissions(mPermissionsArrays, REQUEST_PERMISSION);
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_request_permissions -> {
                requestStorage()
            }
        }
    }
}