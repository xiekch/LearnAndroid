package com.example.myapplication.layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;

public class ProgressBarActivity extends AppCompatActivity {
    private ProgressBar progressBar2;
    private ProgressBar progressBar4;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        progressBar2 = findViewById(R.id.progressbar2);
        progressBar4 = findViewById(R.id.progressbar4);
        button1 = findViewById(R.id.button1);
        handler.sendEmptyMessage(0);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar progressBar = new ProgressBar(ProgressBarActivity.this);

                progressBar4.setVisibility(View.VISIBLE);
                Toast.makeText(ProgressBarActivity.this, "cannot interact now", Toast.LENGTH_SHORT).show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar4.setVisibility(View.GONE);
                    }
                }, 5000);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (progressBar2.getProgress() < 100) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar2.setProgress(progressBar2.getProgress() + 5);
                        handler.sendEmptyMessage(0);
                    }
                }, 500);
            } else {
                Toast.makeText(ProgressBarActivity.this, "finished", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
