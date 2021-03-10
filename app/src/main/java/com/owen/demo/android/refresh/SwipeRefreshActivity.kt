package com.owen.demo.android.refresh

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.owen.demo.android.MainActivity
import com.owen.demo.android.R
import com.owen.recyclerview.demo.MainItemData

/**
 *
 * <br/>Author：yunying.zhang
 * <br/>Email: yunyingzhang@rastar.com
 * <br/>Date: 2020/9/23
 */
class SwipeRefreshActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_swipe_refresh)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_swipe_refresh).apply {

            layoutManager = LinearLayoutManager(this@SwipeRefreshActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }


            adapter = MainActivity.MainListAdapter().apply {
                val list = ArrayList<MainItemData>().apply {
                    add(MainItemData("CustomView", "pie chart"))
                    add(MainItemData("系统界面显示控制", "控制系统状态栏、导航栏显示及隐藏"))
                }
                addData(list)
            }

            addItemDecoration(DividerItemDecoration(this@SwipeRefreshActivity, DividerItemDecoration.VERTICAL))
        }

        findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).apply {
            setOnRefreshListener {
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    isRefreshing = false
                    Toast.makeText(this@SwipeRefreshActivity, "已刷新", Toast.LENGTH_SHORT).show()
                }, 3000)
            }
        }

    }
}