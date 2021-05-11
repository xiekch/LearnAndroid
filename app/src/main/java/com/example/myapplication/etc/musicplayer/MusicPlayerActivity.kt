package com.example.myapplication.etc.musicplayer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.getIntValue
import com.example.myapplication.extensions.getLongValue
import com.example.myapplication.extensions.getStringValue
import kotlinx.android.synthetic.main.activity_music_player.*
import kotlin.concurrent.thread


class MusicPlayerActivity : AppCompatActivity(), View.OnClickListener {
     var isPlaying = false
        set(value) {
            field = value
            if (isPlaying) {
                play_pause_btn.setImageResource(R.drawable.ic_stop_vector)
            } else {
                play_pause_btn.setImageResource(R.drawable.ic_play_vector)
            }
        }

    private val musicList = ArrayList<Music>()

    companion object {
        const val STORAGE_REQUEST = 1
        const val TAG = "MusicPlayerActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        val check = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (check != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                STORAGE_REQUEST
            )
        } else {
            thread {
                init()
            }
        }

        play_pause_btn.setOnClickListener(this)
    }

    private fun init() {
        try {
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DISPLAY_NAME
            )
            val cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
            )
            if (cursor == null) {
                Toast.makeText(this, "cursor is null", Toast.LENGTH_SHORT)
                    .show()
            }
            cursor?.apply {
                if (cursor.moveToFirst()) {
                    do {
                        val id = cursor.getLongValue(MediaStore.Audio.Media._ID)
                        val title = cursor.getStringValue(MediaStore.Audio.Media.TITLE)
                        val duration = cursor.getIntValue(MediaStore.Audio.Media.DURATION) / 1000
                        val music = Music(id, title, duration)
                        musicList.add(music)
                    } while (cursor.moveToNext())
                }
            }

        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }

        runOnUiThread {
            musicRecyclerView.adapter = MusicAdapt(musicList, this)
            musicRecyclerView.layoutManager = LinearLayoutManager(this)
            Intent(this, MusicPlayerService::class.java).apply {
                action = INIT
                putExtra(TRACK_IDS, musicList.map { it.mediaStoreId }.toLongArray())
                startService(this)
            }
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.play_pause_btn -> {
                if (isPlaying) {
                    Intent(this, MusicPlayerService::class.java).apply {
                        action = PAUSE
                        startService(this)
                    }
                } else {
                    Intent(this, MusicPlayerService::class.java).apply {
                        action = PLAY_TRACK
                        startService(this)
                    }
                }
                isPlaying = !isPlaying
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_REQUEST -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                thread { init() }
                Log.i(TAG, "success gain WRITE_EXTERNAL_STORAGE")
            } else {
                Toast.makeText(this, "Please grant WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}