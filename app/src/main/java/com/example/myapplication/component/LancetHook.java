package com.example.myapplication.component;

import android.os.Bundle;
import android.util.Log;

import me.ele.lancet.base.Origin;
import me.ele.lancet.base.annotations.Insert;
import me.ele.lancet.base.annotations.Proxy;
import me.ele.lancet.base.annotations.TargetClass;

public class LancetHook {

    @Insert("onCreate")
    @TargetClass("com.example.myapplication.MainActivity")
    public void onCreate(Bundle savedInstanceState) {
        Log.i("Lancet", "hook");
        Origin.callVoid();
    }

//    @Proxy("i")
//    @TargetClass("android.util.Log")
//    public static int anyName(String tag, String msg) {
//        msg = "lancet" + msg;
//        return (int) Origin.call();
//    }
}
