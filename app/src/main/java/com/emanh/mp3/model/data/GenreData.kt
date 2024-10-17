package com.emanh.mp3.model.data

import com.emanh.mp3.model.GenreModel

class GenreData {
    fun listGenre(): MutableList<GenreModel> {
        val allSong = SongData().listSong()

        val balladsMusic = allSong.filter { it.id in listOf(1, 7, 11, 12, 13, 14, 15, 16, 19, 20, 21) }
        val rapMusic = allSong.filter { it.id in listOf(2, 5, 6, 18, 24) }
        val hipHopMusic = allSong.filter { it.id in listOf(3, 5, 8, 9, 10, 22, 23, 24) }
        val rAndB = allSong.filter { it.id in listOf(4, 25, 26, 27, 28, 29) }

        return mutableListOf(
            GenreModel(
                1,
                "Ballads Music",
                song = ArrayList(balladsMusic)
            ),GenreModel(
                2,
                "Rap Music",
                song = ArrayList(rapMusic)
            ),GenreModel(
                3,
                "Hip-Hop Music",
                song = ArrayList(hipHopMusic)
            ),GenreModel(
                4,
                "R&B Music",
                song = ArrayList(rAndB)
            ),
        )
    }
}