package ru.bikbulatov.pureWave.podcasts.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.bikbulatov.pureWave.MainActivity
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.podcasts.domain.models.TrackModel
import ru.bikbulatov.pureWave.podcasts.ui.PodcastsViewModel


class PodcastsAdapter(
    private val tracks: List<TrackModel>,
    val podcastTitle: String,
    val podcastId: Int,
    val podcastsViewModel: PodcastsViewModel
) :
    RecyclerView.Adapter<PodcastsAdapter.PodcastsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastsViewHolder {
        return PodcastsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_author_compositions, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    override fun onBindViewHolder(holder: PodcastsViewHolder, position: Int) {
        if (position == 0) {
            holder.tvTitle.text = podcastTitle
            holder.tvTitle.visibility = View.VISIBLE
        }
        holder.tvName.text = tracks[position].title
        holder.tvDuration.text = tracks[position].playtimeString
        holder.ivBtnPlay.setOnClickListener {
//            MainActivity.instance.setAudioToPlayer(tracks[position].file)
            AnimationUtils.loadAnimation(holder.ivBtnPlay.context, R.anim.btn_click)
            //todo продумать логику добавления списка треков - не начиная с первого, а начиная с выбранного
            MainActivity.instance.setAudioToPlayer(tracks)
        }
        if (tracks[position].isLiked)
            holder.ivBtnLike.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.colorPrimary
                )
            )
        else
            holder.ivBtnLike.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )

        holder.ivBtnLike.setOnClickListener {
            podcastsViewModel.toggleLike(podcastId, tracks[position].id)
            AnimationUtils.loadAnimation(holder.ivBtnLike.context, R.anim.btn_click)
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