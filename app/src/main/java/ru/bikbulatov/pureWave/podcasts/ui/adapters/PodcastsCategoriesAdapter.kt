package ru.bikbulatov.pureWave.podcasts.ui.adapters

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
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.articles.ui.FragmentSingleArticle
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastCategoryModel
import ru.bikbulatov.pureWave.podcasts.ui.FragmentSinglePodcast
import ru.bikbulatov.pureWave.podcasts.ui.PodcastsViewModel

class PodcastsCategoriesAdapter(
    private val podcasts: List<PodcastCategoryModel>,
    val podcastsViewModel: PodcastsViewModel,
    val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<PodcastsCategoriesAdapter.PodcastsCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastsCategoryViewHolder {
        return PodcastsCategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_podcast_category, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return podcasts.size
    }

    override fun onBindViewHolder(holder: PodcastsCategoryViewHolder, position: Int) {
        var podcastName = podcasts[position].title
        if (podcastName.length > 11) {
            podcastName = podcastName.take(8) + "..."
        }

        holder.tvName.text = podcastName
        holder.tvTracksCount.text = podcasts[position].tracksNum.toString() + " выпусков"
        Glide
            .with(holder.itemView.context)
            .load(podcasts[position].cover)
            .centerCrop()
            .transform(CenterCrop(), RoundedCorners(180))
            .into(holder.ivCover)

        holder.clPodcastRoot.setOnClickListener {
            fragmentManager.commit {
                add(R.id.flContainer, FragmentSinglePodcast())
                addToBackStack(null)
            }
        }
    }

    inner class PodcastsCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCover: ImageView = view.findViewById(R.id.ivCover)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvTracksCount: TextView = view.findViewById(R.id.tvTracksCount)
        val clPodcastRoot: ConstraintLayout = view.findViewById(R.id.clPodcastRoot)
    }
}