package com.example.newsapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.domain.model.News

class NewsAdapter(var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList = ArrayList<News>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.tvTitleItemNews.text = news.webTitle
            binding.tvCategoryItemNews.text = news.sectionName


            binding.cbStarItemNews.isChecked = news.isFavorite == true

            binding.cbStarItemNews.setOnClickListener {
                if (news.isFavorite == null) {
                    news.isFavorite = true
                } else {
                    news.isFavorite = !news.isFavorite!!
                }
                onItemClickListener.onCheckBoxCheckChanged(news.isFavorite!!, news.id)
            }
        }
    }


    override fun getItemCount(): Int {
        return newsList.size
    }

    fun addItems(news: List<News>) {
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onCheckBoxCheckChanged(isChecked: Boolean, id: String)
    }
}