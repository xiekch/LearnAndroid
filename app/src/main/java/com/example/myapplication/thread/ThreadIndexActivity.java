package com.example.myapplication.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ThreadIndexActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int MSG_SEND = 1;
    public static final int MSG_TICK = 11;
    public static final int MSG_TICK_STOP = 12;
    public static final int MSG_DATA = 13;
    public static final int MSG_DATA_STOP = 14;
    private Button button_post_delayed;
    private Button button_send_message;
    private Button button_cancel;
    private ToggleButton button_tick;
    private Switch switch_load_data;
    private TextView textView;
    private Button button_download;

    private Handler handler = new Handler() {
        boolean stop = false;
        int count = 0;

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_SEND:
                    Toast.makeText(ThreadIndexActivity.this, msg.toString(), Toast.LENGTH_SHORT).show();
                    break;
                case MSG_TICK:
                    if (!stop) {
                        count++;
                        Toast.makeText(ThreadIndexActivity.this, "tick " + count, Toast.LENGTH_SHORT).show();
                        this.sendEmptyMessageDelayed(MSG_TICK, 3000);
                    }
                    break;
                case MSG_TICK_STOP:
                    stop = true;
                    break;
                case MSG_DATA:
                    textView.setText(msg.obj.toString());
                    break;
            }
//            Toast.makeText(ThreadIndexActivity.this, "You will not see it", Toast.LENGTH_SHORT).show();
        }
    };

    private HandlerThread handlerThread = new HandlerThread("handler thread");
    private Handler checkDataHandler;
    private DownloadTask downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_index);
        button_post_delayed = findViewById(R.id.button_post_delayed);
        button_send_message = findViewById(R.id.button_send_message);
        button_cancel = findViewById(R.id.button_cancel);
        button_tick = findViewById(R.id.button_tick);
        switch_load_data = findViewById(R.id.switch_load_data);
        textView = findViewById(R.id.textView_data);
        button_download = findViewById(R.id.button_download);

        button_post_delayed.setOnClickListener(this);
        button_send_message.setOnClickListener(this);
        button_cancel.setOnClickListener(this);
        button_tick.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    handler.sendEmptyMessage(MSG_TICK);
                } else handler.sendEmptyMessage(MSG_TICK_STOP);
            }
        });
        button_download.setOnClickListener(this);

        switch_load_data.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!handlerThread.isAlive()) {
                        handlerThread.start();
                    }
                    if (checkDataHandler == null)
                        checkDataHandler = new Handler(handlerThread.getLooper()) {
                            @Override
                            public void handleMessage(@NonNull Message msg) {
                                switch (msg.what) {
                                    case MSG_DATA:
                                        // update data
                                        handler.sendMessage(Message.obtain(handler, MSG_DATA, (int) (Math.random() * 1000)));
                                        checkDataHandler.sendEmptyMessageDelayed(MSG_DATA, 3000);
                                        break;
                                    case MSG_DATA_STOP:
                                        // pause
                                        checkDataHandler.removeCallbacksAndMessages(null);
                                        break;
                                }
                            }
                        };
                    checkDataHandler.sendEmptyMessage(MSG_DATA);
                } else {
                    // pause
                    checkDataHandler.sendEmptyMessage(MSG_DATA_STOP);
                }
            }
        });
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
                        handler.sendMessage(Message.obtain(handler, MSG_SEND));
                    }
                }.start();
                break;

            case R.id.button_cancel:
                handler.removeCallbacksAndMessages(null);
                break;
            case R.id.button_download:
                if (downloadTask == null) {
                    downloadTask = new DownloadTask();
                    downloadTask.execute();
                }
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
        if (checkDataHandler != null) {
            checkDataHandler.removeCallbacksAndMessages(null);
        }
        downloadTask.cancel(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }

    class DownloadTask extends AsyncTask<String, Integer, String> {
        ProgressBar pb = findViewById(R.id.progressBar_download);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            for (int i = 1; i <= 100; i += 2) {
                publishProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return "Failed";
                }
            }
            return "OK";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            pb.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(ThreadIndexActivity.this, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled(String s) {
            Toast.makeText(ThreadIndexActivity.this, "Download Failed", Toast.LENGTH_LONG).show();
        }
    }
}

