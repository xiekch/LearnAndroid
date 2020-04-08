package com.example.myapplication.intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button jump_button1;
    private Button jump_button2;
    private Button jump_button3;
    private Button jump_button4;
    private Button share_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        Log.d("IntentActivity", "create");
        logTaskName();

        jump_button1 = findViewById(R.id.jump_button1);
        jump_button2 = findViewById(R.id.jump_button2);
        jump_button3 = findViewById(R.id.jump_button3);
        jump_button4 = findViewById(R.id.jump_button4);
        share_button = findViewById(R.id.share_button);

        jump_button1.setOnClickListener(this);
        jump_button2.setOnClickListener(this);
        jump_button3.setOnClickListener(this);
        jump_button4.setOnClickListener(this);
        share_button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jump_button1:
                Intent intent1 = new Intent();
                intent1.setAction("android.intent.action.Jump");
                startActivityForResult(intent1, 0);
                break;
            case R.id.jump_button2:
                Intent intent2 = new Intent(IntentActivity.this, IntentActivity.class);
                startActivity(intent2);
                break;
            case R.id.jump_button3:
                Intent read1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.runoob.com"));
                startActivity(read1);
                break;
            case R.id.jump_button4:
                Uri uri = Uri.parse("tel:10086");
                Intent call = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(call);
                break;
            case R.id.share_button:
                Intent share_intent = new Intent();
                share_intent.setAction(Intent.ACTION_SEND); // set action
                share_intent.setType("text/plain"); // set the type to share
                share_intent.putExtra(Intent.EXTRA_SUBJECT, "share"); //add subject
                share_intent.putExtra(Intent.EXTRA_TEXT, "share with you:" + "android"); //add content
                share_intent = Intent.createChooser(share_intent, "share");
                startActivity(share_intent);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(IntentActivity.this, data.getExtras().getString("title"), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(IntentActivity.this, "new Intent", Toast.LENGTH_SHORT).show();
        Log.d("intent", "new Intent");
        logTaskName();
    }

    private void logTaskName() {
        Log.d("IntentActivity", "taskId:" + getTaskId() + " hashcode:" + hashCode());
        try {
            ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Log.d("IntentActivity", activityInfo.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "resume", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "pause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "restart", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "restart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "destroy", Toast.LENGTH_SHORT).show();
        Log.d("life cycle", "destroy");
    }

}


