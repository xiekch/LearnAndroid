package com.example.myapplication.layout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class LayoutIndexActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    public static final String EXTRA_TYPE = "com.example.myapplication.layout.type";
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private MyButton button7;
    private Button button8;
    private Button button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_index);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        setOnClickListener();
    }

    private void setOnClickListener() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnTouchListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(LayoutIndexActivity.this, "click", Toast.LENGTH_SHORT).show();
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button1:
                intent = new Intent(LayoutIndexActivity.this, SysuActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(LayoutIndexActivity.this, WebViewActivity.class);
                intent.putExtra(EXTRA_TYPE, 0);
                startActivity(intent);
                break;
            case R.id.button3:
                intent = new Intent(LayoutIndexActivity.this, WebViewActivity.class);
                intent.putExtra(EXTRA_TYPE, 1);
                startActivity(intent);
                break;
            case R.id.button4:
                Toast toast = new Toast(getApplicationContext());
                View view = LayoutInflater.from(LayoutIndexActivity.this).inflate(R.layout.customized_toast_layout, null);
                toast.setView(view);
                toast.show();
                break;
            case R.id.button5:
                intent = new Intent(LayoutIndexActivity.this, AlertDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.button6:
                intent = new Intent(LayoutIndexActivity.this, ProgressBarActivity.class);
                startActivity(intent);
                break;
            case R.id.button8:
                intent = new Intent(LayoutIndexActivity.this, ActionBarActivity.class);
                startActivity(intent);
                break;
            case R.id.button9:
                intent = new Intent(LayoutIndexActivity.this, ToolBarActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(LayoutIndexActivity.this, "Unexpected id", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.button7:
                // listener is prior to callback MyButton.onTouchEvent
                Toast.makeText(LayoutIndexActivity.this, "Listener", Toast.LENGTH_SHORT).show();
                Log.d("MyButton Listener", "touched");
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("activity", "touched");
        return super.onTouchEvent(event);
    }
}
