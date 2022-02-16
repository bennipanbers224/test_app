package com.example.testui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.klinikapp.DataSource.Layanan.Resource.Resource
import com.example.klinikapp.DataSource.Layanan.Resource.ResourceState
import com.example.testui.Adapter.BeritaAdapter
import com.example.testui.Model.Articles
import com.example.testui.Model.BeritaResponse
import com.example.testui.Resource.BeritaViewModel
import com.example.testui.Resource.injectFeature
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.viewModel
import android.os.Build

import android.app.Activity
import android.graphics.Color


class MainActivity : AppCompatActivity() {

    val vm : BeritaViewModel by viewModel()

    private val beritaAdapter = BeritaAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectFeature()

        loadData()
        observeData()
        setupListView()
        setLightStatusBar(this)
    }
    private fun loadData() {
        vm.showNews("apple","2022-02-15","popularity","f4704ffe16394c2b97bad021c0d11a90")
    }

    private fun observeData() {
        vm.showNewsResult.observe(this, Observer { setAdapter(it) })
    }

    private fun setAdapter(resource: Resource<BeritaResponse>?) {
        resource.let {
            when(it?.state){
                ResourceState.LOADING -> {
                    ll_content.visibility = View.GONE
                    skeleton.visibility = View.VISIBLE
                    skeleton.startShimmerAnimation()
                }
                ResourceState.SUCCESS ->{
                    skeleton.visibility = View.GONE
                    ll_content.visibility = View.VISIBLE
                    tv_news_total.text = "${it.data?.totalResults} artikel"
                    it.data.let {
                        beritaAdapter.setData(it!!.articles)
                    }
                }
                else ->{
                    Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupListView(){
        var duration: Long = 200L

        rv_berita.adapter = beritaAdapter
        rv_berita.layoutManager = LinearLayoutManager(this)

        rv_berita.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(rv_berita.scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    val positionView = (rv_berita.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()

                    if(dy>0 && positionView > 0){
                        ll_menu.visibility = View.GONE
                        ll_menu.animate().translationY(1f)
                        ll_menu.animate().translationX(1f)
                        ll_menu.animate().duration = duration
                    }else{
                        ll_menu.visibility = View.VISIBLE
                        ll_menu.animate().translationY(0f)
                        ll_menu.animate().translationX(0f)
                        ll_menu.animate().duration = duration
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    ll_menu.visibility = View.GONE
                    ll_menu.animate().translationY(1f)
                    ll_menu.animate().translationX(1f)
                    ll_menu.animate().duration = duration
                }else{
                    ll_menu.visibility = View.VISIBLE
                    ll_menu.animate().translationY(0f)
                    ll_menu.animate().translationX(0f)
                    ll_menu.animate().duration = duration
                }

                super.onScrollStateChanged(recyclerView, newState)
            }

        })

        beritaAdapter.onItemClick = {
            articles ->
            var intent = Intent(this, NewsDetail::class.java)
            intent.putExtra("urlImage", articles.urlToImage)
            intent.putExtra("date", articles.publishedAt)
            intent.putExtra("author", articles.author)
            intent.putExtra("desc", articles.desc)
            intent.putExtra("title", articles.title)
            startActivity(intent)
        }
    }

    private fun setLightStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = activity.window.decorView.systemUiVisibility // get current flag
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // add LIGHT_STATUS_BAR to flag
            activity.window.decorView.systemUiVisibility = flags
            activity.window.statusBarColor = Color.WHITE // optional
        }
    }

}