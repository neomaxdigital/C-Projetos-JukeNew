package com.juke.mp3player.data

import com.juke.mp3player.music.ArtworkCrop
import com.juke.mp3player.music.Track

interface LibraryRepository {
    fun tracks(): List<Track>
}

class MockLibraryRepository : LibraryRepository {
    override fun tracks(): List<Track> = listOf(
        Track(1, "A Brighter Tomorrow", "Northlands", "4:28", ArtworkCrop(76, 486, 128, 108)),
        Track(2, "Golden Sky", "Lunabelle", "3:56", ArtworkCrop(76, 613, 128, 108)),
        Track(3, "Midnight Drive", "The Horizon", "4:12", ArtworkCrop(76, 741, 128, 108)),
        Track(4, "Lost in Colors", "Faded Letters", "3:20", ArtworkCrop(76, 868, 128, 108)),
        Track(5, "Ocean Inside", "Blue Whales", "5:03", ArtworkCrop(76, 995, 128, 108)),
        Track(6, "Better Days", "Sunny Vibes", "3:44", ArtworkCrop(76, 1123, 128, 108)),
        Track(7, "Road Trip", "The Nomads", "4:01", ArtworkCrop(76, 1252, 128, 108)),
        Track(8, "Acústico", "Som da Vida", "3:17", ArtworkCrop(76, 1381, 128, 108))
    )
}
