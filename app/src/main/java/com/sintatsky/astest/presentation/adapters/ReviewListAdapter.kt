package com.sintatsky.astest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sintatsky.astest.databinding.ItemReviewBinding
import com.sintatsky.astest.domain.entity.review.ReviewResult

class ReviewListAdapter :
    PagingDataAdapter<ReviewResult, ReviewListAdapter.ReviewViewHolder>(ReviewListDiffCallback()) {
    var onReviewItemSelectedListener: OnReviewItemSelectedListener? = null
    class ReviewViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)

    class ReviewListDiffCallback : DiffUtil.ItemCallback<ReviewResult>() {
        override fun areItemsTheSame(oldItem: ReviewResult, newItem: ReviewResult) =
            oldItem.display_title == newItem.display_title

        override fun areContentsTheSame(oldItem: ReviewResult, newItem: ReviewResult) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = getItem(position)
        with(holder.binding) {
            tvTitle.text = review?.display_title
            tvShortDescription.text = review?.summary_short
            tvAuthor.text = review?.byline
            Glide.with(root).load(review?.multimedia?.src).centerCrop().into(ivTitle)
            ivBookmark.setOnClickListener {
                onReviewItemSelectedListener?.onBookmarkClick()
            }
            root.setOnClickListener {
                onReviewItemSelectedListener?.onRootItemClick(review?.link?.url)
            }
        }
    }

    interface OnReviewItemSelectedListener{
        fun onBookmarkClick()
        fun onRootItemClick(link: String?)
    }

}