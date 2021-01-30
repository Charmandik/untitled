package ru.bikbulatov.pureWave.podcasts.ui.adapters

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastModel
import ru.bikbulatov.pureWave.podcasts.ui.PodcastsViewModel

class PodcastsAdapter(
    private val podcasts: MutableList<PodcastModel>,
    podcastsViewModel: PodcastsViewModel
) :
    RecyclerView.Adapter<PodcastsAdapter.PodcastsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastsViewHolder {
        return PodcastsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_author_compositions, parent, false)
        )
    }

    override fun getItemCount(): Int {
        var count: Int = 0
        for (podcast in podcasts)
            count = podcast.tracks.size
        return count
    }

    override fun onBindViewHolder(holder: PodcastsViewHolder, position: Int) {
        for (i in 0 until podcasts[position].tracks.size-1) {
            if (i == 0) {
                holder.tvTitle.text = podcasts[position].category
                holder.tvTitle.visibility = View.VISIBLE
            }
            holder.tvName.text = podcasts[position].tracks[i].title
            holder.tvDuration.text = podcasts[position].tracks[i].playtimeString
            holder.ivBtnPlay.setOnClickListener {
                val mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(podcasts[position].tracks[i].file)
                    prepare() // might take long! (for buffering, etc)
                    start()
                }
            }
        }
    }

    inner class PodcastsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivBtnLike: ImageView = view.findViewById(R.id.ivBtnLike)
        val ivBtnPlay: ImageView = view.findViewById(R.id.ivBtnPlay)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDuration: TextView = view.findViewById(R.id.tvDuration)
    }
}