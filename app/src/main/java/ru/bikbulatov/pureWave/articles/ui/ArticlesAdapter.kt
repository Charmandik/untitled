package ru.bikbulatov.pureWave.articles.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.articles.models.ArticleModel

class ArticlesAdapter(val articles: List<ArticleModel>, val viewModel: ArticlesVM) :
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
        holder.tvArticleTitle.text = articles[position].title
        holder.tvArticleTitle.setOnClickListener {
            viewModel.getArticle(articles[position].id)
        }
    }

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvArticleTitle: TextView = view.findViewById(R.id.tvArticleTitle)
        val tvNewWarning: TextView = view.findViewById(R.id.tvNewWarning)
    }
}