package com.example.newsapp.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.domain.model.News

class FavoritesAdapter :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private var favoritesList = ArrayList<News>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(favoritesList[position])
    }

    inner class FavoritesViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.tvTitleItemNews.text = news.webTitle
            binding.tvCategoryItemNews.text = news.sectionName
            binding.cbStarItemNews.visibility = View.GONE
        }
    }


    override fun getItemCount(): Int {
        return favoritesList.size
    }

    fun addItems(news: List<News>) {
        favoritesList.clear()
        favoritesList.addAll(news)
        notifyDataSetChanged()
    }
}