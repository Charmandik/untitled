package ru.bikbulatov.pureWave.mainWindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastCategoryModel
import ru.bikbulatov.pureWave.podcasts.ui.FragmentSinglePodcast


class PodcastsPickerAdapter(
    val podcastsCategories: List<PodcastCategoryModel>,
    val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<PodcastsPickerAdapter.PodcastCategorieHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastCategorieHolder {
        return PodcastCategorieHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_podcast_picker, parent, false)
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

        var title = podcastsCategories[position].title
        if (title.length > 17) {
            title = title.take(14) + "..."
        }
        holder.tvName.text = title

        var tracksNum = podcastsCategories[position].tracksNum.toString()
        if (tracksNum.length > 17) {
            tracksNum = tracksNum.take(14) + "..."
        }
        holder.tvTracksCount.text = tracksNum

        holder.clPodcastRoot.setOnClickListener {
            fragmentManager.commit {
                add(R.id.flContainer, FragmentSinglePodcast().apply {
                    arguments = Bundle().apply {
                        putInt("id", podcastsCategories[position].id)
                    }
                })
                addToBackStack(null)
            }
        }
    }

    inner class PodcastCategorieHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCover: ImageView = view.findViewById(R.id.ivCover)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvTracksCount: TextView = view.findViewById(R.id.tvTracksCount)
        val clPodcastRoot: ConstraintLayout = view.findViewById(R.id.clPodcastRoot)
    }
}