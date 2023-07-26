package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.ui.Audio

interface AudioRepository {
    fun playById(id: Long)
    fun pauseById(id: Long)
    fun getAll(): LiveData<List<Audio>>
    fun getNext(): Audio
    fun getCurrent(): Audio?
}
