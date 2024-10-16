package com.emanh.mp3.model.data

import com.emanh.mp3.model.LibraryModel

class LibraryData {
    fun listLibrary(): MutableList<LibraryModel> {
        val allSongs = SongData().listSong()

        val library1 = allSongs.filter { it.id in listOf(1, 4, 6, 12, 15, 20) }
        val library2 = allSongs.filter { it.id in listOf(1, 2, 3, 4, 10, 22, 21, 23, 24) }
        val library3 = allSongs.filter { it.id in listOf(2, 4, 2, 6, 11, 13, 16, 17, 18, 19) }
        val library4 = allSongs.filter { it.id in listOf(1, 3, 4, 5, 6, 9, 10, 11, 20) }
        val library5 = allSongs.filter { it.id in listOf(3, 4, 6, 7, 8, 9, 10, 13, 15, 20) }

        return mutableListOf(
            LibraryModel(
                1,
                "emanh",
                "https://img.freepik.com/premium-photo/human-resources-specialist-digital-avatar-generative-ai_934475-9302.jpg",
                "Music Sad",
                song = ArrayList(library1)
            ), LibraryModel(
                2,
                "emanh",
                "https://img.freepik.com/premium-photo/human-resources-specialist-digital-avatar-generative-ai_934475-9302.jpg",
                "Music Hip-Hop",
                song = ArrayList(library2)
            ), LibraryModel(
                3,
                "emanh",
                "https://img.freepik.com/premium-photo/human-resources-specialist-digital-avatar-generative-ai_934475-9302.jpg",
                "Music Chill",
                song = ArrayList(library3)
            ), LibraryModel(
                4,
                "emanh",
                "https://img.freepik.com/premium-photo/human-resources-specialist-digital-avatar-generative-ai_934475-9302.jpg",
                "Music Homeland",
                song = ArrayList(library4)
            ), LibraryModel(
                5,
                "Đen Vâu",
                "https://cafebiz.cafebizcdn.vn/162123310254002176/2021/7/7/photo-1-162564020223387683391.jpg",
                "Playlist DenVau",
                song = ArrayList(library5)
            )
        )
    }
}