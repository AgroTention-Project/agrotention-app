package com.bangkit.agrotentionapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.agrotentionapp.R
import com.bangkit.agrotentionapp.data.remote.response.DataItem
import com.bangkit.agrotentionapp.databinding.ItemNewsBinding


class NewsAdapter(private val onItemClick: (String) -> Unit) : ListAdapter<DataItem, NewsAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    class NewsViewHolder(private val binding: ItemNewsBinding, private val onItemClick: (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: DataItem) {

            binding.imgNews.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.logo))
            binding.titleNews.text = news.title
            binding.publisherNews.text = news.publisher
            binding.timeNews.text = news.time

            itemView.setOnClickListener {
                onItemClick(news.link)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        if (newsItem != null) {
            holder.bind(newsItem)
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<DataItem> =
            object : DiffUtil.ItemCallback<DataItem>() {
                override fun areItemsTheSame(
                    oldItem: DataItem,
                    newItem: DataItem
                ): Boolean {
                    return oldItem.title == newItem.title
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: DataItem,
                    newItem: DataItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

}


