package com.example.myapplication.layout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_alert_dialog.buttonStyle1
import kotlinx.android.synthetic.main.activity_alert_dialog.buttonStyle2
import kotlinx.android.synthetic.main.activity_alert_dialog.buttonStyle3
import kotlinx.android.synthetic.main.activity_alert_dialog.buttonStyle4

class AlertDialogActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog)

        buttonStyle1.setOnClickListener(this)
        buttonStyle2.setOnClickListener(this)
        buttonStyle3.setOnClickListener(this)
        buttonStyle4.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonStyle1 -> {
                val builder1 = AlertDialog.Builder(this@AlertDialogActivity)
                builder1.setTitle("Question").setMessage("How do you feel")
                    .setPositiveButton("Great") { dialog, which ->
                        Toast.makeText(
                            this@AlertDialogActivity,
                            "Keep good feelings every day",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton("Bad") { dialog, which ->
                        Toast.makeText(
                            this@AlertDialogActivity,
                            "forget the pain",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setIcon(R.drawable.notification).show()
                Log.d("AlertDialogActivity", "asynchronous message")
            }
            R.id.buttonStyle2 -> {
                val builder2 = AlertDialog.Builder(this@AlertDialogActivity)
                val array2 = arrayOf("male", "female")
                builder2.setTitle("Your sex").setSingleChoiceItems(array2, 0) { dialog, which ->
                    Toast.makeText(this@AlertDialogActivity, array2[which], Toast.LENGTH_SHORT)
                        .show()
                    dialog.dismiss()
                }.setCancelable(false).show()
            }
            R.id.buttonStyle3 -> {
                val builder3 = AlertDialog.Builder(this@AlertDialogActivity)
                val array3 = arrayOf("basketball", "football", "tennis", "golf")
                val selected = booleanArrayOf(false, false, false, false)
                builder3.setTitle("Your interests").setMultiChoiceItems(
                    array3,
                    selected
                ) { dialog, which, isChecked ->
                    Toast.makeText(
                        this@AlertDialogActivity,
                        array3[which],
                        Toast.LENGTH_SHORT
                    ).show()
                }
                    .setPositiveButton("ok") { dialog, which -> }
                    .setNegativeButton("cancel") { dialog, which -> }.show()
            }
            R.id.buttonStyle4 -> {
                startActivity(Intent(this, DialogActivity::class.java))
            }
        }
    }
}