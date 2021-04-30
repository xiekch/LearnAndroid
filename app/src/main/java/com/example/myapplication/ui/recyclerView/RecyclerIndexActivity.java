package com.example.myapplication.ui.recyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;

public class RecyclerIndexActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_index);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        setListener();
    }

    private void setListener() {
        button1.setOnClickListener(new OnClick());
        button2.setOnClickListener(new OnClick());
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.button1:
                    Toast.makeText(RecyclerIndexActivity.this, R.string.ListLayout, Toast.LENGTH_SHORT).show();
                    intent = new Intent(RecyclerIndexActivity.this, LinearLayoutRecyclerViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button2:
                    Toast.makeText(RecyclerIndexActivity.this, R.string.StaggeredGrid, Toast.LENGTH_SHORT).show();
                    intent = new Intent(RecyclerIndexActivity.this, StaggeredGridRecyclerViewActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
