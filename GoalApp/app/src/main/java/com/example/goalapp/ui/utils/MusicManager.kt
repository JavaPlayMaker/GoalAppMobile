package com.example.goalapp.ui.utils

import android.content.Context
import android.media.MediaPlayer
import com.example.goalapp.R

object MusicManager {
    private var mediaPlayer: MediaPlayer? = null

    fun start(context: Context) {
        if (!isMusicEnabled(context)) return

        if (mediaPlayer == null) {
            val resId = context.resources.getIdentifier("background_music", "raw", context.packageName)
            if (resId != 0) {
                try {
                    mediaPlayer = MediaPlayer.create(context, resId)?.apply {
                        isLooping = true
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
            }
        }
    }

    fun stop() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause() // Use pause instead of stop for smoother transitions if needed
                // If you truly want to stop and release, use release()
            }
        }
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun setMusicEnabled(context: Context, enabled: Boolean) {
        val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("music_enabled", enabled).apply()
        
        if (enabled) {
            start(context)
        } else {
            stop()
        }
    }

    fun isMusicEnabled(context: Context): Boolean {
        val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        return prefs.getBoolean("music_enabled", true)
    }
}
