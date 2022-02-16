package com.example.testui.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.klinikapp.DataSource.inflate
import com.example.testui.Model.Articles
import com.example.testui.R
import kotlinx.android.synthetic.main.news_item.view.*

class BeritaAdapter : RecyclerView.Adapter<BeritaAdapter.ViewHolderItem>() {


    private val items by lazy { mutableListOf<Articles>() }
    var onItemClick : ((Articles) -> Unit)? = null

    fun setData(data: List<Articles>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    open inner class ViewHolderItem(parent: ViewGroup, newsItem: Int):
        RecyclerView.ViewHolder(parent.inflate(newsItem)){
        fun bind(articles: Articles) {
            itemView.tv_news_title.text = articles.title
            itemView.tv_news_date.text = articles.publishedAt
            itemView.tv_news_desc.text = articles.desc
            itemView.tv_news_sumber.text = articles.author
            Glide.with(itemView.context).load(articles.urlToImage).into(itemView.iv_news)
            itemView.ll_item.setOnClickListener {
                onItemClick?.invoke(articles)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        return ViewHolderItem(parent, R.layout.news_item)
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        return holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}