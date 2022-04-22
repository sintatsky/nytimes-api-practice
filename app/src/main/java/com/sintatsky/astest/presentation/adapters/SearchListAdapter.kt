package com.sintatsky.astest.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sintatsky.astest.databinding.ItemSearchBinding
import com.sintatsky.astest.domain.entity.search.Doc
import com.sintatsky.astest.domain.entity.search.Multimedia

class SearchListAdapter : PagingDataAdapter<Doc, SearchListAdapter.SearchViewHolder>(SearchDiffCallback()) {

    class SearchViewHolder(val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root)
    class SearchDiffCallback: DiffUtil.ItemCallback<Doc>() {
        override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val doc = getItem(position)
        holder.binding.tvSnippet.text = doc?.snippet
        holder.binding.tvLeadParagraph.text = doc?.lead_paragraph
        holder.binding.tvByLiner.text = doc?.byline?.original
        val multimedia = doc?.multimedia as ArrayList<Multimedia>
        var imageUrl = ""
        for (media in multimedia){
            if (media.type == "image" && media.subType == "master495"){
                imageUrl = BASE_IMAGE_URL + media.url
                break
            }
        }
        Glide.with(holder.binding.root).load(imageUrl).centerCrop().into(holder.binding.ivTitle)
    }

    companion object{
        const val BASE_IMAGE_URL = "https://www.nytimes.com/"
    }
}