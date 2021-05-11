package com.example.myapplication.etc.musicplayer

import android.app.Service
import android.content.ContentUris
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.os.PowerManager
import android.provider.MediaStore
import android.util.Log
import java.io.IOException
import java.util.*

class MusicPlayerService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener {

    companion object {
        const val TAG = "MusicPlayerService"
        var mPlayer: MediaPlayer? = null
        var mTracks = ArrayList<Long>()
        var mCurr = 0
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            INIT -> handleInit(intent)
            PREVIOUS -> handlePrevious()
            PAUSE -> pauseTrack()
            NEXT -> handleNext()
            PLAY_TRACK -> playTrack(intent)
            FINISH -> handleFinish(startId)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun handleInit(intent: Intent) {
        initMediaPlayerIfNeeded()
        val tracks = intent.extras?.getLongArray(TRACK_IDS) ?: longArrayOf()
        mTracks.clear()
        mTracks.addAll(tracks.toList())
        mCurr = 0
    }

    private fun pauseTrack() {
        mPlayer?.pause()
    }

    private fun playTrack(intent: Intent) {
        initMediaPlayerIfNeeded()
        var trackId = intent.extras?.getLong(TRACK_ID)
        if (trackId == null) {
            if (mTracks.isEmpty()) return
            else trackId = mTracks[mCurr]
        }
        val trackIndex = mTracks.indexOf(trackId)
        if (trackIndex == -1) {
            mTracks.add(trackId)
            mCurr = mTracks.lastIndex
        } else {
            mCurr = trackIndex
        }

        setTrack(trackId)
    }

    private fun handlePrevious() {
        val previousTrackId = when (mTracks.size) {
            0 -> return
            1 -> mTracks[0]
            else -> mTracks[(mCurr - 1 + mTracks.size) % (mTracks.size)]
        }
        setTrack(previousTrackId)
    }

    private fun handleNext() {
        val nextTrackId = when (mTracks.size) {
            0 -> return
            1 -> mTracks[0]
            else -> mTracks[(mCurr + 1) % (mTracks.size)]
        }
        setTrack(nextTrackId)
    }

    private fun handleFinish(startId: Int) {
        stopSelf(startId)
    }

    private fun initMediaPlayerIfNeeded() {
        if (mPlayer != null) {
            return
        }

        mPlayer = MediaPlayer().apply {
            setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
            setOnPreparedListener(this@MusicPlayerService)
            setOnCompletionListener(this@MusicPlayerService)
            setOnErrorListener(this@MusicPlayerService)
        }
    }


    private fun setTrack(wantedTrackId: Long) {
        if (mTracks.isEmpty() || wantedTrackId == 0L) {
            return
        }

        initMediaPlayerIfNeeded()
        mPlayer?.reset() ?: return
        val trackIndex = mTracks.indexOf(wantedTrackId)
        if (trackIndex == -1) {
            return
        }
        mCurr = trackIndex

        try {
            val trackUri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                wantedTrackId
            )

            mPlayer!!.setDataSource(applicationContext, trackUri)
            mPlayer!!.prepareAsync()
        } catch (e: IOException) {
            Log.e(TAG, e.toString())
        } catch (ignored: Exception) {
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mPlayer?.start()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return false
    }

    override fun onCompletion(mp: MediaPlayer?) {
        mp?.reset()
    }


    override fun onDestroy() {
        super.onDestroy()
        mPlayer?.stop()
        mPlayer?.release()
        mPlayer = null
    }

}