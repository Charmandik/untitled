package ru.bikbulatov.pureWave.mainWindow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastCategorieModel


class PodcastsCategoriesAdapter(val podcastsCategories: List<PodcastCategorieModel>) :
    RecyclerView.Adapter<PodcastsCategoriesAdapter.PodcastCategorieHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastCategorieHolder {
        return PodcastCategorieHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_podcast_categorie_preview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return podcastsCategories.size
    }

    override fun onBindViewHolder(holder: PodcastCategorieHolder, position: Int) {
        val myOptions = RequestOptions()
            .fitCenter() // or centerCrop
            .override(128, 128)
        Glide
            .with(holder.itemView.context)
            .asBitmap()
            .apply(myOptions)
            .load(podcastsCategories[position].cover)
            .centerCrop()
            .transform(CenterCrop(), RoundedCorners(25))
            .into(holder.ivCover)
        holder.tvName.text = podcastsCategories[position].title
        holder.tvTracksCount.text = podcastsCategories[position].tracksNum.toString()
    }

    inner class PodcastCategorieHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCover: ImageView = view.findViewById(R.id.ivCover)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvTracksCount: TextView = view.findViewById(R.id.tvTracksCount)
    }
}