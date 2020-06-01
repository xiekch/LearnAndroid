package com.example.myapplication.storage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Map;

public class SharedPreferencesActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Button button_save;
    private Button button_display;
    private EditText edit_text;
    private TextView text_view_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        button_save = findViewById(R.id.button_save);
        button_display = findViewById(R.id.button_display);
        edit_text = findViewById(R.id.edit_text);
        text_view_display = findViewById(R.id.text_view_display);

        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        button_save.setOnClickListener(this);
        button_display.setOnClickListener(this);
        edit_text.setOnClickListener(this);
        text_view_display.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                String text = edit_text.getText().toString();
                if (!text.equals("")) {
                    editor.putString(String.valueOf(System.currentTimeMillis()), text);
                    editor.apply();
                    Toast.makeText(SharedPreferencesActivity.this, "saved", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_display:
                Map<String, ?> all = sharedPreferences.getAll();
                text_view_display.setText("");
                for (Map.Entry<String, ?> entry : all.entrySet()) {
                    text_view_display.append(entry.getKey() + " " + entry.getValue() + "\n");
                }

                break;
        }
    }
}
