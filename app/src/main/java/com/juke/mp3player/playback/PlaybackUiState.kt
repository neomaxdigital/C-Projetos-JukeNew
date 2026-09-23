package com.juke.mp3player.playback

import com.juke.mp3player.music.Track

data class PlaybackUiState(
    val currentTrack: Track? = null,
    val playing: Boolean = false
)

/**
 * Contract kept deliberately small so the mock UI can later receive the
 * Media3-backed controller migrated from the approved player.
 */
interface PlaybackController {
    val state: PlaybackUiState
    fun play(track: Track)
    fun previous()
    fun togglePlayPause()
    fun next()
}
