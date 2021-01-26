package ru.bikbulatov.pureWave.podcasts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.podcasts.domain.models.TrackModel

class PodcastsAdapter(
    private val podcasts: List<TrackModel>,
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
        return podcasts.size
    }

    override fun onBindViewHolder(holder: PodcastsViewHolder, position: Int) {
        holder.tvName.text = podcasts[position].title
        holder.tvDuration.text = podcasts[position].playtimeString
    }

    inner class PodcastsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivBtnLike: ImageView = view.findViewById(R.id.ivBtnLike)
        val ivBtnPlay: ImageView = view.findViewById(R.id.ivBtnPlay)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDuration: TextView = view.findViewById(R.id.tvDuration)
    }
}