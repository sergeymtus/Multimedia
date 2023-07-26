package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.AudioRepository
import ru.netology.nmedia.repository.AudioRepositoryImpl
import ru.netology.nmedia.ui.Audio

class AudioViewModel : ViewModel() {
    private val repository: AudioRepository = AudioRepositoryImpl()
    val data = repository.getAll()

    fun playById(id: Long) = repository.playById(id)
    fun pauseById(id: Long) = repository.pauseById(id)
    fun getNext(): Audio = repository.getNext()
}
