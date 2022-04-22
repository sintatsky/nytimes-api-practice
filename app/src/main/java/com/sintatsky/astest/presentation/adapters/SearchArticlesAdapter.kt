package com.sintatsky.astest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sintatsky.astest.databinding.ItemSearchBinding
import com.sintatsky.astest.domain.entity.search.Doc
import com.sintatsky.astest.domain.entity.search.Multimedia

class SearchArticlesAdapter  :
    RecyclerView.Adapter<SearchArticlesAdapter.ImageViewHolder>() {
    class ImageViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)
    var onSearchArticleItemSelectedListener: OnSearchArticleItemSelectedListener? = null
    private val diffCallback = object : DiffUtil.ItemCallback<Doc>() {
            override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean {
                return oldItem == newItem
            }
        }

    private val differ = AsyncListDiffer(this, diffCallback)

    var articles: List<Doc>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

//    private var onItemClickListener: ((Doc) -> Unit)? = null
//
//    fun setOnItemClickListener(listener: (Doc) -> Unit) {
//        onItemClickListener = listener
//    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val doc = articles[position]
        holder.binding.apply {
            tvSnippet.text = doc.snippet
            tvLeadParagraph.text =doc.lead_paragraph
            val multimedia = doc.multimedia as ArrayList<Multimedia>
            var imageUrl = ""
            for (media in multimedia){
                if (media.type == "image" && media.subType == "master495"){
                    imageUrl = SearchListAdapter.BASE_IMAGE_URL + media.url
                    break
                }
            }
            Glide.with(holder.binding.root).load(imageUrl).centerCrop().into(holder.binding.ivTitle)
            root.setOnClickListener {
                onSearchArticleItemSelectedListener?.onArticleItemClick(doc.web_url)
//                onItemClickListener?.let { click ->
//                    click(doc)
//                }
            }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    interface OnSearchArticleItemSelectedListener{
        fun onArticleItemClick(link: String)
    }
}
