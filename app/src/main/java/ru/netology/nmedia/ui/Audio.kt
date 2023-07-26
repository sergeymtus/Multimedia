package ru.netology.nmedia.ui

data class Audio(
    val id: Long,
    var songUrl: String? = null,
    var isPlaying: Boolean = false,
)
