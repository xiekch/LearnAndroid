package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.LifeCycleActivity;
import com.example.myapplication.fragment.ContainerActivity;
import com.example.myapplication.intent.IntentActivity;
import com.example.myapplication.layout.LayoutIndexActivity;
import com.example.myapplication.recyclerView.RecyclerIndexActivity;
import com.example.myapplication.storage.StorageIndexActivity;
import com.example.myapplication.thread.ThreadIndexActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private TextView textView1_1;
    private TextView textView1_2;
    private TextView textView1_3;
    private TextView textView2_1;
    private TextView textView2_2;
    private TextView textView2_3;
    private TextView textView3_1;
    private TextView textView3_2;
    private TextView textView3_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1_1 = findViewById(R.id.view1_1);
        textView1_2 = findViewById(R.id.view1_2);
        textView1_3 = findViewById(R.id.view1_3);
        textView2_1 = findViewById(R.id.view2_1);
        textView2_2 = findViewById(R.id.view2_2);
        textView2_3 = findViewById(R.id.view2_3);
        textView3_1 = findViewById(R.id.view3_1);
        textView3_2 = findViewById(R.id.view3_2);
        textView3_3 = findViewById(R.id.view3_3);

        textView1_1.setText(R.string.layout);
        textView1_2.setText(R.string.Recyclerview);
        textView1_3.setText(R.string.animation);
        textView2_1.setText(R.string.components);
        textView2_2.setText("Intent");
        textView2_3.setText("Fragment");
        textView3_1.setText(R.string.thread);
        textView3_2.setText(R.string.storage);
        textView3_3.setText(R.string.exit);

        OnClick onClick = new OnClick();
        textView1_1.setOnClickListener(onClick);
        textView1_2.setOnClickListener(onClick);
        textView1_3.setOnClickListener(onClick);
        textView2_1.setOnClickListener(onClick);
        textView2_2.setOnClickListener(onClick);
        textView2_3.setOnClickListener(onClick);
        textView3_1.setOnClickListener(onClick);
        textView3_2.setOnClickListener(onClick);
        textView3_3.setOnClickListener(onClick);
    }

    /**
     * Called when the user taps the Send button
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.view1_1:
                    intent = new Intent(MainActivity.this, LayoutIndexActivity.class);
                    startActivity(intent);
                    break;
                case R.id.view1_2:
                    intent = new Intent(MainActivity.this, RecyclerIndexActivity.class);
                    startActivity(intent);
                    break;
                case R.id.view1_3:
                    intent = new Intent(MainActivity.this, AnimationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.view2_1:
                    intent = new Intent(MainActivity.this, LifeCycleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.view2_2:
                    intent = new Intent();
                    intent.setClass(MainActivity.this, IntentActivity.class);
                    startActivity(intent);
                    break;
                case R.id.view2_3:
                    intent = new Intent(MainActivity.this, ContainerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.view3_1:
                    intent = new Intent(MainActivity.this, ThreadIndexActivity.class);
                    startActivity(intent);
                    break;
                case R.id.view3_2:
                    intent = new Intent(MainActivity.this, StorageIndexActivity.class);
                    startActivity(intent);
                    break;
                case R.id.view3_3:
                    finish();
                    break;
            }
        }
    }
}
