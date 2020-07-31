package com.example.myapplication.fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.fragment.AFragment.OnAMessageListener
import com.example.myapplication.fragment.BFragment.OnBMessageListener
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : AppCompatActivity(), AFragment.OnFragmentInteractionListener, BFragment.OnFragmentInteractionListener, View.OnClickListener, OnAMessageListener, OnBMessageListener {
    private var aFragment: AFragment? = null
    private var bFragment: BFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        change_fragmentA_button.setOnClickListener(this)
        change_fragmentB_button.setOnClickListener(this)
    }

    override fun onFragmentInteraction(uri: Uri) {}
    override fun onClick(v: View) {
        when (v.id) {
            R.id.change_fragmentA_button -> initTransactionA()
            R.id.change_fragmentB_button -> initTransactionB()
        }
    }

    private fun initTransactionA() {
        val transaction = supportFragmentManager.beginTransaction()
        if (aFragment == null) {
            aFragment = AFragment.newInstance("Dynamic set A text")
            transaction.add(R.id.frameLayout, aFragment!!)
        }
        if (bFragment != null) {
            transaction.hide(bFragment!!)
        }
        transaction.show(aFragment!!).addToBackStack(null).commitAllowingStateLoss()
    }

    private fun initTransactionB() {
        val transaction = supportFragmentManager.beginTransaction()
        if (bFragment == null) {
            bFragment = BFragment.newInstance("Dynamic set B text")
            transaction.add(R.id.frameLayout, bFragment!!)
        }
        if (aFragment != null) {
            transaction.hide(aFragment!!)
        }
        transaction.show(bFragment!!).addToBackStack(null).commitAllowingStateLoss()
    }

    override fun setMessage(text: String) {
        textView_message.text = text
    }
}