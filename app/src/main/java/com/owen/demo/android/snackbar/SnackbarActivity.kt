package com.owen.demo.android.snackbar

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.owen.demo.android.R

/**
 *
 * <br/>Author：yunying.zhang
 * <br/>Email: yunyingzhang@rastar.com
 * <br/>Date: 2020/9/24
 */
class SnackbarActivity: AppCompatActivity(), View.OnClickListener {

    val TAG = "SnackbarTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_snackbar)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_show_snackbar_tips -> {
                val s = Snackbar.make(findViewById(R.id.linearLayout), "这是测试Snackbar", Snackbar.LENGTH_LONG)
                s.show()
            }
            R.id.btn_show_snackbar_tips2 -> {
                val s = Snackbar.make(findViewById(R.id.linearLayout), "这是测试Snackbar2", Snackbar.LENGTH_INDEFINITE)
                s.setAction("撤销") {
                    Toast.makeText(this@SnackbarActivity, "动作按钮被点击", Toast.LENGTH_SHORT).show()
                }
                s.show()
            }
            R.id.btn_show_snackbar_tips3 -> {
                val s = Snackbar.make(findViewById(R.id.linearLayout), "这是测试Snackbar3", Snackbar.LENGTH_INDEFINITE)
                s.setAction("撤销") {
                    Toast.makeText(this@SnackbarActivity, "动作按钮被点击", Toast.LENGTH_SHORT).show()
                }
                s.setBackgroundTint(Color.CYAN)
                s.setTextColor(Color.BLACK)
                s.setActionTextColor(Color.RED)
                s.setBackgroundTintMode(PorterDuff.Mode.SRC)
                s.show()
            }
            R.id.btn_show_snackbar_tips4 -> {
                val s = Snackbar.make(findViewById(R.id.linearLayout), "这是测试Snackbar4", Snackbar.LENGTH_INDEFINITE)
                s.setAction("撤销") {
                    Toast.makeText(this@SnackbarActivity, "动作按钮被点击", Toast.LENGTH_SHORT).show()
                }
                s.addCallback(object: Snackbar.Callback() {
                    override fun onShown(snackbar: Snackbar?) {
                        super.onShown(snackbar)
                        Toast.makeText(this@SnackbarActivity, "Snackbar显示", Toast.LENGTH_SHORT).show()
                    }

                    override fun onDismissed(snackbar: Snackbar?, event: Int) {
                        super.onDismissed(snackbar, event)
                        Toast.makeText(this@SnackbarActivity, "Snackbar消失 envent -> ${event.let {
                            when(it) {
                                Snackbar.Callback.DISMISS_EVENT_ACTION -> "用户点击操作"
                                Snackbar.Callback.DISMISS_EVENT_TIMEOUT -> "延时超时"
                                Snackbar.Callback.DISMISS_EVENT_SWIPE -> "用户滑动操作"
                                Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE -> "新的Snackbar显示"
                                Snackbar.Callback.DISMISS_EVENT_MANUAL -> "dismiss()接口"
                                else -> "未知操作"

                            }
                        }}", Toast.LENGTH_SHORT).show()
                    }
                })
                s.show()
            }
            R.id.btn_show_snackbar_tips5 -> {
                val s = Snackbar.make(findViewById(R.id.btn_show_snackbar_tips), "这是测试Snackbar5", Snackbar.LENGTH_INDEFINITE)
                s.setAction("撤销") {
                    Toast.makeText(this@SnackbarActivity, "动作按钮被点击", Toast.LENGTH_SHORT).show()
                }
                s.setAnchorView(R.id.bottomAnchor)
                s.animationMode = Snackbar.ANIMATION_MODE_FADE
                s.show()
            }
            R.id.btn_show_snackbar_tips6 -> {
                val s = Snackbar.make(findViewById(R.id.btn_show_snackbar_tips), "", Snackbar.LENGTH_INDEFINITE)
                val layout = s.view as Snackbar.SnackbarLayout
                layout.setPadding(0, 0, 0, 0)

                val contentView = LayoutInflater.from(this@SnackbarActivity).inflate(R.layout.layout_snackbar_custom, null)
                val imageView = contentView.findViewById<ImageView>(R.id.imageView)
                val textView = contentView.findViewById<TextView>(R.id.textView)
                val button = contentView.findViewById<Button>(R.id.button)


                imageView.setImageResource(android.R.drawable.ic_dialog_alert)
                textView.text = "记录已删除"

                button.text = "撤销"
                button.setOnClickListener {
                    s.dismiss()
                    Toast.makeText(this@SnackbarActivity, "删除操作已撤销", Toast.LENGTH_SHORT).show()
                }

                layout.addView(contentView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                contentView.bringToFront()
                s.show()
            }
        }
    }
}