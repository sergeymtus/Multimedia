package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardAudioBinding
import ru.netology.nmedia.ui.Audio

interface OnInteractionListener {
    fun onPlay(audio: Audio) {}
}

class AudioAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Audio, AudioViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val binding = CardAudioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AudioViewHolder(
            binding,
            onInteractionListener
        )
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audio = getItem(position)
        holder.bind(audio)
    }
}

class AudioViewHolder(
    private val binding: CardAudioBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(audio: Audio) {
        binding.apply {
            tvTitle.setText("Song " + audio.id)
            if (audio.isPlaying) {
                btnPlay.text = "Pause"
                icon.setBackgroundResource(R.drawable.pause)
            } else {
                btnPlay.text = "Play"
                icon.setBackgroundResource(R.drawable.play)
            }
            btnPlay.setOnClickListener {
                val audioUrl = audio.songUrl
                if (audioUrl != null) {
                    onInteractionListener.onPlay(audio)
                }
            }
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Audio>() {
    override fun areItemsTheSame(oldItem: Audio, newItem: Audio): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Audio, newItem: Audio): Boolean {
        return oldItem == newItem
    }
}

/*
class AudioAdapter(
    private val requireContext: Context,
    private val audioList: ArrayList<Audio>
) :
    RecyclerView.Adapter<AudioAdapter.ViewHolder>() {
    lateinit var mediaPlayer: MediaPlayer

    var playingPosition = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val btnplay = itemView.findViewById<TextView>(R.id.btnPlay)
        val icon = itemView.findViewById<TextView>(R.id.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(requireContext).inflate(R.layout.card_audio, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val audio = audioList[position]
        val no = position + 1
        holder.title.setText("Song " + no)
        holder.btnplay.setOnClickListener {
            val audioUrl = audio.songUrl
            if (audioUrl != null) {
                if (holder.btnplay.text.equals("Play")) {
                    if (playingPosition == 0) {
                        playAudio(audioUrl)
                        holder.btnplay.text = "Pause"
                        holder.icon.setBackgroundResource(R.drawable.pause)
                        playingPosition = position
                        audio.isPlaying = true
                    } else {
                        mediaPlayer.stop()
                        mediaPlayer.reset()
                        mediaPlayer.release()

                        playAudio(audioUrl)
                        holder.btnplay.text = "Pause"
                        holder.icon.setBackgroundResource(R.drawable.pause)
                        playingPosition = position
                        audio.isPlaying = true
                    }

                } else {
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                    mediaPlayer.release()
                    Toast.makeText(requireContext, "Audio has been paused", Toast.LENGTH_SHORT)
                        .show()
                    holder.btnplay.text = "Play"
                    holder.icon.setBackgroundResource(R.drawable.play)
                    audio.isPlaying = false
                }
            }
        }
    }

    override fun getItemCount() = audioList.size

    private fun playAudio(audioUrl: String) {

        mediaPlayer = MediaPlayer()

        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )

        try {
            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener { mp ->
                mp.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Toast.makeText(requireContext, "Audio started playing..", Toast.LENGTH_SHORT).show()
    }
}*/
