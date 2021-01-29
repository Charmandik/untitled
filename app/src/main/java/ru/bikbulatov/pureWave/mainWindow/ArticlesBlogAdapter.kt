package ru.bikbulatov.pureWave.mainWindow

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.articles.models.ArticleModel
import ru.bikbulatov.pureWave.articles.ui.FragmentSingleArticle


class ArticlesBlogAdapter(
    private val articlesBlog: List<ArticleModel>,
    val fragmentManager: FragmentManager
) :
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
            .override(256, 256)

        Glide
            .with(holder.itemView.context)
            .asBitmap()
            .apply(myOptions)
            .load(articlesBlog[position].cover)
            .centerCrop()
            .transform(CenterCrop(), RoundedCorners(25))
            .into(holder.ivIcon)

        var title = articlesBlog[position].title
        if (title.length > 17) {
            title = title.take(14) + "..."
        }
        holder.tvTitle.text = title

        var intro = articlesBlog[position].intro
        if (intro.length > 17) {
            intro = intro.take(14) + "..."
        }
        holder.tvAuthorName.text = Html.fromHtml(intro)
        holder.clArticleRoot.setOnClickListener {
            fragmentManager
                .beginTransaction()
                .add(R.id.flContainer, FragmentSingleArticle().apply {
                    arguments = Bundle().apply {
                        putInt("id", articlesBlog[position].id)
                    }
                })
                .addToBackStack(null)
                .commit()
        }
    }

    inner class ArticlesBlogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.ivIcon)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvAuthorName: TextView = view.findViewById(R.id.tvAuthorName)
        val clArticleRoot: ConstraintLayout = view.findViewById(R.id.clArticleRoot)
    }
}