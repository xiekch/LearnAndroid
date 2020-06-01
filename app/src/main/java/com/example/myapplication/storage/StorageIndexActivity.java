package com.example.myapplication.storage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class StorageIndexActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_shared_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_index);

        button_shared_preferences = findViewById(R.id.button_shared_preferences);

        button_shared_preferences.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button_shared_preferences:
                intent = new Intent(StorageIndexActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
                break;
        }
    }
}
