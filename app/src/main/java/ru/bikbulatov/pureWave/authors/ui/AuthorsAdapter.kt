package ru.bikbulatov.pureWave.authors.ui

import android.graphics.Paint
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
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.authors.models.AuthorModel

class AuthorsAdapter(
    val authors: List<AuthorModel>,
    val viewModel: AuthorsVM,
    val isNeedSpan: Boolean,
    val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<AuthorsAdapter.AuthorViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        return AuthorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_author_preview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return authors.size
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        var authorName = authors[position].name
        if (authorName.length > 19) {
            authorName = authorName.take(14) + "..."
        }
        holder.tvAuthorName.text = authorName
        if (isNeedSpan)
            holder.tvAuthorName.paintFlags =
                holder.tvAuthorName.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        var description = authors[position].position
        if (description.length > 19) {
            description = description.take(14) + "..."
        }
        holder.tvDescription.text = description

        Glide
            .with(holder.itemView.context)
            .load(authors[position].photo)
            .centerCrop()
            .transform(CenterCrop(), RoundedCorners(180))
            .into(holder.ivAuthor)
        holder.clAuthorPreviewRoot.setOnClickListener {
            viewModel.getAuthor(authors[position].id)
            fragmentManager
                .beginTransaction()
                .add(R.id.flContainer, FragmentSingleAuthor())
                .addToBackStack(null)
                .commit()
        }
    }

    inner class AuthorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAuthor: ImageView = view.findViewById(R.id.ivAuthor)
        val tvAuthorName: TextView = view.findViewById(R.id.tvAuthorName)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val clAuthorPreviewRoot: ConstraintLayout = view.findViewById(R.id.clAuthorPreviewRoot)
    }
}