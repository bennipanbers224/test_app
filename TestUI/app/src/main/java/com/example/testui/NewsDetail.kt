package com.example.testui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.toolbar_detail.*

class NewsDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        setupView()
        setupToolbar()
    }

    private fun setupView() {
        tv_news_date.text = intent.getStringExtra("date")
        tv_news_sumber.text = intent.getStringExtra("author")
        tv_news_desc.text = intent.getStringExtra("desc")
        tv_news_title.text = intent.getStringExtra("title")
        Glide.with(applicationContext).load(intent.getStringExtra("urlImage")).into(iv_news)
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.setTitle("Berita")
        toolBar.setTitleTextColor(Color.parseColor("#ffffff"))
        toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }


}