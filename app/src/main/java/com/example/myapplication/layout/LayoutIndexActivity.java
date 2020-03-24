package com.example.myapplication.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class LayoutIndexActivity extends AppCompatActivity {
    public static final String EXTRA_TYPE = "com.example.myapplication.layout.type";
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_index);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        setOnClickListener();
    }

    private void setOnClickListener() {
        OnClick onClick = new OnClick();
        button1.setOnClickListener(onClick);
        button2.setOnClickListener(onClick);
        button3.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
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
            }
        }
    }
}
