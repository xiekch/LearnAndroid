package com.example.myapplication.library

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import dagger.Component
import kotlinx.android.synthetic.main.activity_dagger2.button_inject
import javax.inject.Inject

class Dagger2Activity : AppCompatActivity() {
    private val man = Man()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger2)
        button_inject.setOnClickListener { man.sayHello(this) }
    }
}

class Man {
    @Inject
    lateinit var car: Car

    init {
        DaggerManComponent.create().injectMan(this)
    }

    fun sayHello(context: Context) {
        if (::car.isInitialized) {
            Toast.makeText(context, "I have a car", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(context, "I don't have a car", Toast.LENGTH_SHORT).show()
    }
}

class Car @Inject constructor()

@Component
interface ManComponent {
    fun injectMan(man: Man)
}