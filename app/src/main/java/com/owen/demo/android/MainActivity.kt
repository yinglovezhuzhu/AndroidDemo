package com.owen.demo.android

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owen.demo.android.anim.AnimActivity
import com.owen.demo.android.anim.DynamicAnimActivity
import com.owen.demo.android.clipboard.ClipboardActivity
import com.owen.demo.android.cview.PieChartActivity
import com.owen.demo.android.drawable.PaletteActivity
import com.owen.demo.android.drawable.VectorDrawableActivity
import com.owen.demo.android.pip.PIPActivity
import com.owen.demo.android.refresh.SwipeRefreshActivity
import com.owen.demo.android.snackbar.SnackbarActivity
import com.owen.demo.android.ui.UIActivity
import com.owen.recyclerview.demo.MainItemData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview).apply {

            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }


            adapter = MainListAdapter().apply {
                val list = ArrayList<MainItemData>().apply {
                    add(MainItemData("CustomView", "pie chart"))
                    add(MainItemData("系统界面显示控制", "控制系统状态栏、导航栏显示及隐藏"))
                    add(MainItemData("刷新控件", "SwipeRefreshLayout刷新控件"))
                    add(MainItemData("Snackbar提示", "Snackbar提示消息"))
                    add(MainItemData("剪切板", "系统剪切板示例"))
                    add(MainItemData("画中画", "展示画中画效果"))
                    add(MainItemData("Android动画效果", "动画示例"))
                    add(MainItemData("VectorDrawable", "矢量图"))
                    add(MainItemData("动力学动画", "DynamicAnimation"))
                    add(MainItemData("调色板", "Pallete调色板库"))
                }
                addData(list)
            }
        }

        recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        recyclerView.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {

            var gestureDetector: GestureDetectorCompat

            init {
                // 定义GestureDetectorCompat对象，快速解析触摸事件，分发为onClick和onLongClick
                gestureDetector = GestureDetectorCompat(this@MainActivity, object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapUp(e: MotionEvent?): Boolean {
                        // 处理点击事件
                        e?.also {
                            val child = recyclerView.findChildViewUnder(e.x, e.y)

                            if(null != child) {
                                val position = recyclerView.getChildAdapterPosition(child)
                                Toast.makeText(this@MainActivity, "Item $position was clicked", Toast.LENGTH_SHORT).show()
                                when(position) {
                                    0 -> {
                                        startActivity(Intent(this@MainActivity, PieChartActivity::class.java))
                                    }
                                    1 -> {
                                        startActivity(Intent(this@MainActivity, UIActivity::class.java))
                                    }
                                    2 -> {
                                        startActivity(Intent(this@MainActivity, SwipeRefreshActivity::class.java))
                                    }
                                    3 -> {
                                        startActivity(Intent(this@MainActivity, SnackbarActivity::class.java))
                                    }
                                    4 -> {
                                        startActivity(Intent(this@MainActivity, ClipboardActivity::class.java))
                                    }
                                    5 -> {
                                        startActivity(Intent(this@MainActivity, PIPActivity::class.java))
                                    }
                                    6 -> {
                                        startActivity(Intent(this@MainActivity, AnimActivity::class.java))
                                    }
                                    7 -> {
                                        startActivity(Intent(this@MainActivity, VectorDrawableActivity::class.java))
                                    }
                                    8 -> {
                                        startActivity(Intent(this@MainActivity, DynamicAnimActivity::class.java))
                                    }
                                    9 -> {
                                        startActivity(Intent(this@MainActivity, PaletteActivity::class.java))
                                    }
                                }
                            }
                        }
                        return super.onSingleTapUp(e)
                    }

                    override fun onLongPress(e: MotionEvent?) {
                        // 处理长按事件
                        e?.also {
                            val child = recyclerView.findChildViewUnder(e.x, e.y)

                            if(null != child) {
                                val position = recyclerView.getChildAdapterPosition(child)
                                Toast.makeText(this@MainActivity, "Item $position was long clicked", Toast.LENGTH_SHORT).show()
                            }
                        }
                        super.onLongPress(e)
                    }

                })
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

                // 调用GestureDetectorCompat对象处理分发事件
                gestureDetector.onTouchEvent(e)

                // 此处不要返回true，否则点击效果将会失效
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }

        })

        android.Manifest.permission.READ_EXTERNAL_STORAGE

        android.Manifest.permission.WRITE_EXTERNAL_STORAGE

        val array = Array<kotlin.String>(2) {
            android.Manifest.permission.READ_EXTERNAL_STORAGE

            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        }
        ActivityCompat.requestPermissions(this, array, 1000)

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    class MainListAdapter() : RecyclerView.Adapter<ItemViewHolder>() {

        private val data = ArrayList<MainItemData>()

        fun addData(d: ArrayList<MainItemData>) {
            if(d.isNotEmpty()) {
                data.addAll(d)
            }
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            // 创建ViewHolder对象
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_1, parent, false)

            return ItemViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            // 获取项目的数量
            return data.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            // 绑定ViewHolder，这里设置需要展示的数据
            holder.title.text = data[position].title
            holder.desc.text = data[position].desc
        }

    }

    interface OnItemClickListener {
        abstract fun onItemClick(holder: ItemViewHolder, position: Int)
    }

}