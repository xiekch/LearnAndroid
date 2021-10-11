package com.example.myapplication.component

import android.app.IntentService
import android.content.Intent
import android.util.Log
import javax.inject.Inject

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
class MyIntentService : IntentService("MyIntentService") {
    override fun onCreate() {
        super.onCreate()
        Log.i("MyIntentService", "onCreate Thread id: " + Thread.currentThread().id)
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i("MyIntentService", "onHandleIntent Thread id: " + Thread.currentThread().id)
        Thread.sleep(1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MyIntentService", "onDestroy Thread id: " + Thread.currentThread().id)
    }
}

class Wheel
class Engine
interface Inject {
    fun setWheel(wheel: Wheel)
    fun setEngine(engine: Engine)
}

//class Car(private var wheel: Wheel, private var engine: Engine) : Inject {
//    override fun setWheel(wheel: Wheel) {
//        this.wheel = wheel
//    }
//
//    override fun setEngine(engine: Engine) {
//        this.engine = engine
//    }
//}

interface InjectCar {
    fun setWheel(wheel: Wheel)
    fun setEngine(engine: Engine)
}

class Car: InjectCar {
    private lateinit var wheel: Wheel
    private lateinit var engine: Engine
    override fun setWheel(wheel: Wheel) {
        this.wheel = wheel
    }

    override fun setEngine(engine: Engine) {
        this.engine = engine
    }
}

//class Car {
//    @Inject
//    lateinit var wheel: Wheel
//
//    @Inject
//    lateinit var engine: Engine
//}