package com.example.myapplication.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.fragment.AFragment.OnAMessageListener
import com.example.myapplication.fragment.BFragment.OnBMessageListener
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : AppCompatActivity(), AFragment.OnFragmentInteractionListener,
    BFragment.OnFragmentInteractionListener, View.OnClickListener, OnAMessageListener,
    OnBMessageListener {
    private val TAG_A = "AFragment"
    private val TAG_B = "BFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "${hashCode()}")
        setContentView(R.layout.activity_container)
        change_fragmentA_button.setOnClickListener(this)
        change_fragmentB_button.setOnClickListener(this)
        initTransactionA()
    }

    override fun onFragmentInteraction(uri: Uri?) {}
    override fun onClick(v: View) {
        when (v.id) {
            R.id.change_fragmentA_button -> initTransactionA()
            R.id.change_fragmentB_button -> initTransactionB()
        }
    }

    private fun initTransactionA() {
        val transaction = supportFragmentManager.beginTransaction()
        var aFragment = supportFragmentManager.findFragmentByTag(TAG_A)
        if (aFragment == null) {
            aFragment = AFragment.newInstance("Dynamic set A text")
            transaction.add(R.id.frameLayout, aFragment, TAG_A)
        }
        val bFragment = supportFragmentManager.findFragmentByTag(TAG_B)
        if (bFragment != null) {
            transaction.hide(bFragment)
        }
        transaction.show(aFragment).addToBackStack(null).commitAllowingStateLoss()
    }

    private fun initTransactionB() {
        val transaction = supportFragmentManager.beginTransaction()
        var bFragment = supportFragmentManager.findFragmentByTag(TAG_B)
        if (bFragment == null) {
            bFragment = BFragment.newInstance("Dynamic set B text")
            transaction.add(R.id.frameLayout, bFragment, TAG_B)
        }
        val aFragment = supportFragmentManager.findFragmentByTag(TAG_A)
        if (aFragment != null) {
            transaction.hide(aFragment)
        }
        transaction.show(bFragment).addToBackStack(null).commitAllowingStateLoss()
    }

    override fun setMessage(text: String) {
        textView_message.text = text
    }

    companion object {
        const val TAG = "ContainerActivity"
    }
}