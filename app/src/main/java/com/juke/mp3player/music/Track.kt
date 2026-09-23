package com.juke.mp3player.music

data class ArtworkCrop(
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int
)

enum class ArtworkFallback {
    NIGHT_SKY,
    NATURE,
    CONCERT,
    CITY
}

data class Track(
    val id: Long,
    val title: String,
    val artist: String,
    val duration: String,
    val artworkCrop: ArtworkCrop? = null,
    val artworkFallback: ArtworkFallback? = null
)
