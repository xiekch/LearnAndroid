package com.example.myapplication.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ThreadIndexActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler handler;
    private Button button_post_delayed;
    private Button button_send_message;
    private Button button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_index);

        if (handler == null) handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Toast.makeText(ThreadIndexActivity.this, msg.toString(), Toast.LENGTH_SHORT).show();
                Looper.loop();
                Toast.makeText(ThreadIndexActivity.this, "You will not see it", Toast.LENGTH_SHORT).show();
            }
        };
        button_post_delayed = findViewById(R.id.button_post_delayed);
        button_send_message = findViewById(R.id.button_send_message);
        button_cancel = findViewById(R.id.button_cancel);

        button_post_delayed.setOnClickListener(this);
        button_send_message.setOnClickListener(this);
        button_cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_post_delayed:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ThreadIndexActivity.this, "post delayed", Toast.LENGTH_SHORT).show();
                    }
                }, 2500);
                break;
            case R.id.button_send_message:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }.start();
                break;

            case R.id.button_cancel:
                handler.removeCallbacksAndMessages(null);
                break;
        }
    }
}

