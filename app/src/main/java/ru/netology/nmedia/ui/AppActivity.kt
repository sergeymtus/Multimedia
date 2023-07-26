package ru.netology.nmedia.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.AudioAdapter
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.databinding.ActivityAppBinding
import ru.netology.nmedia.viewmodel.AudioViewModel

class AppActivity : AppCompatActivity() {

    val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
    }

    val viewModel: AudioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = AudioAdapter(object : OnInteractionListener {
            override fun onPlay(audio: Audio) {

                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                }
                if (audio.isPlaying) {
                    viewModel.pauseById(audio.id)
                    Toast.makeText(
                        this@AppActivity,
                        "Audio has been paused",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    playAudio(audio)
                    viewModel.playById(audio.id)

                    Toast.makeText(this@AppActivity, "Audio started playing..", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { list ->
            adapter.submitList(list)
        }
    }

    private fun playAudio(audio: Audio) {
        mediaPlayer.setOnCompletionListener {
            val nextAudio = viewModel.getNext()
            mediaPlayer.stop()
            mediaPlayer.reset()
            playAudio(viewModel.getNext())
            viewModel.playById(nextAudio.id)
        }

        mediaPlayer.setDataSource(audio.songUrl)
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener { mp ->
            mp.start()
        }
    }
}

