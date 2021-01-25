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
import ru.bikbulatov.pureWave.articles.models.ArticleModel


class ArticlesBlogAdapter(private val articlesBlog: List<ArticleModel>) :
    RecyclerView.Adapter<ArticlesBlogAdapter.ArticlesBlogViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesBlogViewHolder {
        return ArticlesBlogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article_blog, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return articlesBlog.size
    }

    override fun onBindViewHolder(holder: ArticlesBlogViewHolder, position: Int) {
        val myOptions = RequestOptions()
            .fitCenter() // or centerCrop
            .override(128, 128)

        Glide
            .with(holder.itemView.context)
            .asBitmap()
            .apply(myOptions)
            .transform(CenterCrop(), RoundedCorners(25))
            .load(articlesBlog[position].cover)
            .centerCrop()
            .into(holder.ivIcon)
        holder.tvTitle.text = articlesBlog[position].title
        holder.tvAuthorName.text = articlesBlog[position].title
    }

    inner class ArticlesBlogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.ivIcon)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvAuthorName: TextView = view.findViewById(R.id.tvAuthorName)
    }
}