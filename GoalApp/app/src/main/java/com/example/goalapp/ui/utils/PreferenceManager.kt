package com.example.goalapp.ui.utils

import android.content.Context
import android.media.MediaPlayer

object PreferenceManager {
    private var mediaPlayer: MediaPlayer? = null
    private const val PREFS_NAME = "settings"
    private const val KEY_MUSIC_ENABLED = "music_enabled"
    private const val KEY_BLACK_THEME_SUGGESTION = "black_theme_suggestion"

    // --- Music Logic ---

    fun startMusic(context: Context) {
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

    fun stopMusic() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
            }
        }
    }

    fun releaseMusic() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun setMusicEnabled(context: Context, enabled: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_MUSIC_ENABLED, enabled).apply()
        
        if (enabled) {
            startMusic(context)
        } else {
            stopMusic()
        }
    }

    fun isMusicEnabled(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_MUSIC_ENABLED, true)
    }

    // --- Suggestion Theme Logic ---

    fun setBlackThemeForSuggestion(context: Context, enabled: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_BLACK_THEME_SUGGESTION, enabled).apply()
    }

    fun isBlackThemeForSuggestion(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_BLACK_THEME_SUGGESTION, false)
    }
}
