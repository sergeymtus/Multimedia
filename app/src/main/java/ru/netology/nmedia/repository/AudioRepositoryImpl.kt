package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.ui.Audio

class AudioRepositoryImpl : AudioRepository {
    private var audioList = listOf(
        Audio(
            1,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/1.mp3",
            false
        ),
        Audio(
            2,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/2.mp3",
            false
        ),
        Audio(
            3,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/3.mp3",
            false
        ),
        Audio(
            4,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/4.mp3",
            false
        ),
        Audio(
            5,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/5.mp3",
            false
        ),
        Audio(
            6,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/6.mp3",
            false
        ),
        Audio(
            7,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/7.mp3",
            false
        ),
        Audio(
            8,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/8.mp3",
            false
        ),
        Audio(
            9,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/9.mp3",
            false
        ),
        Audio(
            10,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/10.mp3",
            false
        ),
        Audio(
            11,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/11.mp3",
            false
        ),
        Audio(
            12,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/12.mp3",
            false
        ),
        Audio(
            13,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/13.mp3",
            false
        ),
        Audio(
            14,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/14.mp3",
            false
        ),
        Audio(
            15,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/15.mp3",
            false
        ),
        Audio(
            16,
            "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/16.mp3",
            false
        ),
    )

    private val data = MutableLiveData(audioList)

    override fun getAll(): LiveData<List<Audio>> = data

    override fun playById(id: Long) {

        audioList = audioList.map {
            it.copy(isPlaying = false)
        }

        audioList = audioList.map {
            if (it.id == id) it.copy(isPlaying = true)
            else it
        }
        data.value = audioList
    }

    override fun pauseById(id: Long) {
        audioList = audioList.map {
            it.copy(isPlaying = false)
        }
        data.value = audioList
    }

    override fun getNext(): Audio {
        val playingNext: Audio
        val playingAudio = audioList.filter { audio -> audio.isPlaying }
        playingNext = if (playingAudio[0].id >= 16) {
            audioList[0]
        } else {
            val ind = playingAudio[0].id.toInt()
            audioList[ind]
        }
        return playingNext
    }

    override fun getCurrent(): Audio? {
        val playingAudio = audioList.filter { audio -> audio.isPlaying }
        return if (playingAudio.isEmpty()) {
            null
        } else playingAudio[0]
    }
}