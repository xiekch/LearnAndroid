package com.example.myapplication.layout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;

public class AlertDialogActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

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
            switch (v.getId()) {
                case R.id.button1:
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AlertDialogActivity.this);
                    builder1.setTitle("Question").setMessage("How do you feel").setPositiveButton("Great", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(AlertDialogActivity.this, "Keep good feelings every day", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("Bad", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(AlertDialogActivity.this, "forget the pain", Toast.LENGTH_SHORT).show();
                        }
                    }).setIcon(R.drawable.notification).show();
                    break;
                case R.id.button2:
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(AlertDialogActivity.this);
                    final String[] array2 = {"male", "female"};
                    builder2.setTitle("Your sex").setSingleChoiceItems(array2, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(AlertDialogActivity.this, array2[which], Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }).setCancelable(false).show();
                    break;

                case R.id.button3:
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(AlertDialogActivity.this);
                    final String[] array3 = {"basketball", "football", "tennis", "golf"};
                    boolean[] selected = {false, false, false, false};
                    builder3.setTitle("Your interests").setMultiChoiceItems(array3, selected, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            Toast.makeText(AlertDialogActivity.this, array3[which], Toast.LENGTH_SHORT).show();
                        }
                    }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                    break;
            }
        }
    }

}
