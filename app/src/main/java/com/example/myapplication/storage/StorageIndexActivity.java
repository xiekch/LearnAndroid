package com.example.myapplication.storage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import java.io.OutputStream;

public class StorageIndexActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_shared_preferences;
    private Button button_files;
    private static final int PICK_A_FILE_CODE = 711;
    private static final String TAG = "StorageIndexActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_index);

        button_shared_preferences = findViewById(R.id.button_shared_preferences);
        button_files = findViewById(R.id.button_files);

        button_shared_preferences.setOnClickListener(this);
        button_files.setOnClickListener(this);
        findViewById(R.id.buttonScopedStorage).setOnClickListener(this);
        findViewById(R.id.buttonPickAFile).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button_shared_preferences:
                intent = new Intent(StorageIndexActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
                break;
            case R.id.button_files:
                intent = new Intent(StorageIndexActivity.this, FilesActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonScopedStorage:
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.apple_pic);
                insertImage(bitmap);
                break;
            case R.id.buttonPickAFile:
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                // intent.setType(“image/*”);//选择图片
                // intent.setType(“audio/*”); //选择音频
                // intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                // intent.setType(“video/*;image/*”);//同时选择视频和图片
                intent.setType("image/*"); // 无类型限制
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, PICK_A_FILE_CODE);
                break;
        }
    }

    private void insertImage(Bitmap bitmap) {
        // 拿到 MediaStore.Images 表的uri
        Uri tableUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        // 创建图片索引
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "test");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/test");
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());

        // 将该索引信息插入数据表，获得图片的Uri
        Uri imageUri = getContentResolver().insert(tableUri, values);
        try {
            // 通过图片uri获得输出流
            OutputStream os = getContentResolver().openOutputStream(imageUri);

            // 图片压缩保存
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == PICK_A_FILE_CODE) {
            Uri uri = data.getData();
            if (uri == null) return;
            Log.i(TAG, "" + getPath(uri));
        }
    }

    private String getPath(Uri uri) {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(
                    uri,
                    new String[]{
                            MediaStore.MediaColumns.DATA,
                            MediaStore.MediaColumns.DISPLAY_NAME,
                            MediaStore.MediaColumns.RELATIVE_PATH,
                            MediaStore.Images.Media.RELATIVE_PATH},
                    null,
                    null,
                    null
            );
            if (cursor != null && cursor.moveToFirst()) {
                Log.i(TAG, "path " + cursor.getString(0));
                Log.i(TAG, "name " + cursor.getString(1));
                Log.i(TAG, "relative path " + cursor.getString(2));
                Log.i(TAG, "relative path " + cursor.getString(3));
                return cursor.getString(0);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return "";
    }
}
