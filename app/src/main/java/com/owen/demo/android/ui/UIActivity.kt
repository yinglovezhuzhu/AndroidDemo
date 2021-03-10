package com.owen.demo.android.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.owen.demo.android.R

/**
 *
 * <br/>Author：yunying.zhang
 * <br/>Email: yunyingzhang@rastar.com
 * <br/>Date: 2020/9/16
 */
class UIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_ui)


        window?.decorView?.setOnSystemUiVisibilityChangeListener {
            Log.e("SYSTEM_UI", "System ui changed: {$it}")
            if(View.SYSTEM_UI_FLAG_FULLSCREEN and it == 0) {
                Log.e("SYSTEM_UI", "System ui visible")
            } else {
                Log.e("SYSTEM_UI", "System ui invisible")
            }
        }

        findViewById<Button>(R.id.btn_ui_low_profile).setOnClickListener {
            // 调暗系统栏，只能设置window的decorView，在window的attributes中设置是无效的
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE

//            window?.attributes?.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
        }

        findViewById<Button>(R.id.btn_ui_show).setOnClickListener {
            window?.decorView?.systemUiVisibility = 0
        }

        findViewById<Button>(R.id.btn_ui_hide_status_bar).setOnClickListener {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                // 如果之类使用了SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN，那么西面隐藏操作栏就会失败，
                // 使用SYSTEM_UI_FLAG_LAYOUT_STABLE就可以隐藏操作栏
                actionBar?.hide()
            } else {
                window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        findViewById<Button>(R.id.btn_ui_show_status_bar).setOnClickListener {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                window?.decorView?.systemUiVisibility = 0
                actionBar?.show()
            } else {
                window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        findViewById<Button>(R.id.btn_ui_behind_status_bar).setOnClickListener {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                // 只有API 16开始才生效
                window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }

        findViewById<Button>(R.id.btn_ui_hide_navigation_bar).setOnClickListener {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        findViewById<Button>(R.id.btn_ui_full_screen_1).setOnClickListener {
            // 向后倾斜式，全屏后点击屏幕任何位置恢复原样，恢复后不会自动再次进去全屏
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                // 只有API 16开始才生效
                window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            }
        }

        findViewById<Button>(R.id.btn_ui_full_screen_2).setOnClickListener {
            // 沉浸式，全屏后点击跑屏幕区域不会恢复原样，触摸状态栏顶部和导航栏底部的时候，恢复原样，恢复后不会自动再次进去全屏
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                // 只有API 19开始才生效
                window?.decorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE
            }
        }

        findViewById<Button>(R.id.btn_ui_full_screen_3).setOnClickListener {
            // 沉浸式，全屏后点击跑屏幕区域不会恢复原样，触摸状态栏顶部和导航栏底部的时候，状态栏和导航栏悬浮在内容上面，短暂事件不操作后会自动恢复全屏
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                // 只有API 19开始才生效
                window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }
}