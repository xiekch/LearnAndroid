package com.example.myapplication.etc.musicplayer

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


class MusicAdapt(private val musicList: List<Music>, private val context: MusicPlayerActivity) :
    RecyclerView.Adapter<MusicAdapt.MusicViewHolder>() {
    inner class MusicViewHolder(musicItem: LinearLayout) :
        RecyclerView.ViewHolder((musicItem)) {
        var name: TextView = musicItem.findViewById(R.id.musicName)
        var duration: TextView = musicItem.findViewById(R.id.musicDuration)
        var mediaStoreId: Long? = null

        init {
            musicItem.setOnClickListener {
                mediaStoreId?.let { id ->
                    Intent(context, MusicPlayerService::class.java).apply {
                        action = PLAY_TRACK
                        putExtra(TRACK_ID, id)
                        context.startService(this)
                    }
                    context.isPlaying = true
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val musicItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.music_item, parent, false) as LinearLayout
        return MusicViewHolder(musicItem)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.name.text = musicList[position].title
        holder.duration.text = "${musicList[position].duration} s"
        holder.mediaStoreId = musicList[position].mediaStoreId
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

}