package com.example.myapplication.component;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class LifeCycleActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_layout);
        Toast.makeText(LifeCycleActivity.this, "create", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "create");
        foo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(LifeCycleActivity.this, "start", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(LifeCycleActivity.this, "resume", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(LifeCycleActivity.this, "pause", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "pause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(LifeCycleActivity.this, "restart", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "restart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(LifeCycleActivity.this, "stop", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(LifeCycleActivity.this, "destroy", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "destroy");
    }

    public void foo() {
        Log.i("life cycle", "foo");
    }
}
