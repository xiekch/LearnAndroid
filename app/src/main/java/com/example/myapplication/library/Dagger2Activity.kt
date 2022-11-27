package com.example.myapplication.library

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_dagger2.button_inject

class Dagger2Activity : AppCompatActivity() {
    private val man = Man()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger2)
        button_inject.setOnClickListener { man.takeVehicle(this) }
    }
}

class Man @Inject constructor() {
    @Inject
    lateinit var vehicle: Vehicle

    init {
        DaggerManComponent.create().injectMan(this)
    }

    fun takeVehicle(context: Context) {
        if (::vehicle.isInitialized) {
            Toast.makeText(
                context,
                "I have a vehicle. %s".format(vehicle.run()),
                Toast.LENGTH_SHORT
            )
                .show()
        } else Toast.makeText(context, "I don't have a car", Toast.LENGTH_SHORT).show()
    }
}

interface Vehicle {
    fun run(): String
}

class Car @Inject constructor() : Vehicle {
    override fun run(): String {
        return "The car is running"
    }
}

class Bicycle constructor(private val size: Int) : Vehicle {
    override fun run(): String {
        return "The bicycle is running. Size is $size"
    }
}

@Module
class BicycleModule {
    @Provides
    fun provideBicycle(): Bicycle {
        return Bicycle(10)
    }
}

@Module//(includes = [BicycleModule::class])
interface VehicleModule {
    @Binds
    fun bindVehicle(vehicle: Bicycle): Vehicle
}

@Component(modules = [VehicleModule::class, BicycleModule::class])
interface ManComponent {
    fun injectMan(man: Man)
//    fun injectMan():Man
}