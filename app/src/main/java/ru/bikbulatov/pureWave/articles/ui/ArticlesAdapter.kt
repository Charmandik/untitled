package ru.bikbulatov.pureWave.articles.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.articles.models.ArticleModel

class ArticlesAdapter(
    val articles: List<ArticleModel>,
    val viewModel: ArticlesVM,
    val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        if (position == 0) {
            holder.llLikesCount.visibility = View.INVISIBLE
            holder.tvNewWarning.visibility = View.VISIBLE
        } else {
            holder.llLikesCount.visibility = View.VISIBLE
            holder.tvNewWarning.visibility = View.INVISIBLE
        }
        holder.tvArticleTitle.text = articles[position].title
        holder.clArticleRoot.setOnClickListener {
            viewModel.getArticle(articles[position].id)
            fragmentManager.commit {
                add(R.id.flContainer, FragmentSingleArticle().apply {
                    arguments = Bundle().apply { putInt("id", articles[position].id) }
                })
                addToBackStack(null)
            }
        }
        holder.tvLikesCount.text = articles[position].likes.toString()
        if (articles[position].isLiked)
            holder.ivHeartBtn.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.colorPrimary
                )
            )
        else
            holder.ivHeartBtn.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )
        holder.ivHeartBtn.setOnClickListener {
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.btn_click)
            viewModel.toggleLike(articles[position].id)
        }
    }

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvArticleTitle: TextView = view.findViewById(R.id.tvArticleTitle)
        val tvLikesCount: TextView = view.findViewById(R.id.tvLikesCount)
        val ivHeartBtn: ImageView = view.findViewById(R.id.ivHeartBtn)
        val llLikesCount: LinearLayout = view.findViewById(R.id.llLikesCount)
        val tvNewWarning: TextView = view.findViewById(R.id.tvNewWarning)
        val clArticleRoot: ConstraintLayout = view.findViewById(R.id.clArticleRoot)
    }
}