package com.owen.demo.android.drawable

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.owen.demo.android.R

/**
 *
 * <br/>Author：yunying.zhang
 * <br/>Email: yunyingzhang@rastar.com
 * <br/>Date: 2021/2/22
 */
class PaletteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_palette)

        val bm = BitmapFactory.decodeResource(resources, R.drawable.pic)

        val gridView = findViewById<GridView>(R.id.gridView)
        val adapter = GridAdapter(PaletteActivity@ this)
        gridView.adapter = adapter

        val title = findViewById<TextView>(R.id.title)

        val tvLightVibrant = findViewById<TextView>(R.id.tv_light_vibrant)
        val tvVibrant = findViewById<TextView>(R.id.tv_vibrant)
        val tvDarkVibrant = findViewById<TextView>(R.id.tv_dark_vibrant)
        val tvLightMuted = findViewById<TextView>(R.id.tv_light_muted)
        val tvMuted = findViewById<TextView>(R.id.tv_muted)
        val tvDarkMuted = findViewById<TextView>(R.id.tv_dark_muted)


//        Thread(Runnable {
            // 获取调色板实例的操作如果在UI主线程中进行，请务必要使用同步方式
            val palette = Palette.from(bm)
                .maximumColorCount(16)
//                .setRegion(50, 50, 400, 400)
//                .addFilter(Palette.Filter { rgb, hsl ->
//                    rgb != Color.argb(0xff, 0x28, 0x48, 0x88)
//                })
//                .generate()
                .generate(object : Palette.PaletteAsyncListener {
                    override fun onGenerated(palette: Palette?) {
                        palette?.lightVibrantSwatch?.apply {
                            Log.e("XXXX", "lightVibrant: ${color2String(rgb)}")
                            supportActionBar?.setBackgroundDrawable(ColorDrawable(palette.lightVibrantSwatch!!.rgb))
                        }
                        palette?.vibrantSwatch?.apply {
                            Log.e("XXXX", "vibrant: ${color2String(rgb)}")
                        }
                        palette?.darkVibrantSwatch?.apply {
                            Log.e("XXXX", "darkVibrant: ${color2String(rgb)}")
                        }
                        palette?.lightMutedSwatch?.apply {
                            Log.e("XXXX", "lightMuted: ${color2String(rgb)}")
                        }
                        palette?.mutedSwatch?.apply {
                            Log.e("XXXX", "muted: ${color2String(rgb)}")
                        }
                        palette?.darkMutedSwatch?.apply {
                            Log.e("XXXX", "darkMuted: ${color2String(rgb)}")
                        }

                        palette?.swatches?.forEach {
                            Log.e("AAAA", color2String(it.rgb))
                        }
                        if (null != palette) {
                            adapter.addDatas(palette.swatches)
                        }

//                        palette?.apply {
//                            if(null == palette.lightVibrantSwatch) {
//                                tvLightVibrant.setText("未能获取到Light Vibrant颜色配置")
//                            } else {
//                                tvLightVibrant.setBackgroundColor(palette.lightVibrantSwatch!!.rgb)
//                                tvLightVibrant.setTextColor(palette.lightVibrantSwatch!!.bodyTextColor)
//                                tvLightVibrant.setText("Light Vibrant(${color2String(palette.lightVibrantSwatch!!.rgb)})")
//                            }
//                            if(null == palette.vibrantSwatch) {
//                                tvVibrant.setText("未能获取到Vibrant颜色配置")
//                            } else {
//                                tvVibrant.setBackgroundColor(palette.vibrantSwatch!!.rgb)
//                                tvVibrant.setTextColor(palette.vibrantSwatch!!.bodyTextColor)
//                                tvVibrant.setText("Vibrant(${color2String(palette.vibrantSwatch!!.rgb)})")
//                            }
//                            if(null == palette.darkVibrantSwatch) {
//                                tvDarkVibrant.setText("未能获取到Dark Vibrant颜色配置")
//                            } else {
//                                tvDarkVibrant.setBackgroundColor(palette.darkVibrantSwatch!!.rgb)
//                                tvDarkVibrant.setTextColor(palette.darkVibrantSwatch!!.bodyTextColor)
//                                tvDarkVibrant.setText("Dark Vibrant(${color2String(palette.darkVibrantSwatch!!.rgb)})")
//                            }
//
//                            if(null == palette.lightMutedSwatch) {
//                                tvLightMuted.setText("未能获取到Light Muted颜色配置")
//                            } else {
//                                tvLightMuted.setBackgroundColor(palette.lightMutedSwatch!!.rgb)
//                                tvLightMuted.setTextColor(palette.lightMutedSwatch!!.bodyTextColor)
//                                tvLightMuted.setText("Light Muted(${color2String(palette.lightMutedSwatch!!.rgb)})")
//                            }
//                            if(null == palette.mutedSwatch) {
//                                tvMuted.setText("未能获取到Muted颜色配置")
//                            } else {
//                                tvMuted.setBackgroundColor(palette.mutedSwatch!!.rgb)
//                                tvMuted.setTextColor(palette.mutedSwatch!!.bodyTextColor)
//                                tvMuted.setText("Muted(${color2String(palette.mutedSwatch!!.rgb)})")
//                            }
//                            if(null == palette.darkMutedSwatch) {
//                                tvDarkMuted.setText("未能获取到Dark Muted颜色配置")
//                            } else {
//                                tvDarkMuted.setBackgroundColor(palette.darkMutedSwatch!!.rgb)
//                                tvDarkMuted.setTextColor(palette.darkMutedSwatch!!.bodyTextColor)
//                                tvDarkMuted.setText("Dark Muted(${color2String(palette.darkMutedSwatch!!.rgb)})")
//                            }
//
//                            palette.swatches.forEach {
//                                Log.e("AAAA", color2String(it.rgb))
//                            }
//                            adapter.addDatas(palette.swatches)
////                            supportActionBar?.setBackgroundDrawable(ColorDrawable(palette.swatches[0].rgb))
//                        }
                    }
                })




//        }).start()


        findViewById<ImageView>(R.id.imageView).setImageBitmap(bm)



        gridView.visibility = View.VISIBLE
        findViewById<View>(R.id.ll_config_content).visibility = View.GONE



    }


}

class GridAdapter(val context: Context) : BaseAdapter() {

    val data = ArrayList<Palette.Swatch>()

    fun addDatas(values: List<Palette.Swatch>): Unit {
        if(values.isNotEmpty()) {
            data.addAll(values)
            notifyDataSetChanged()
        }
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var textView:TextView?
        if(null == convertView) {
            textView = TextView(context)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            textView.gravity = Gravity.CENTER
            textView.setPadding(10, 20, 10, 20)
        } else {
            textView = convertView as TextView
        }

        textView.setBackgroundColor(data[position].rgb)
        textView.setTextColor(data[position].bodyTextColor)
        textView.text = color2String(data[position].rgb)

        return textView
    }

}

fun color2String(color: Int): String {
    val a = (color shr 24) and 0x0000ff
    val r = (color shr 16) and 0x0000ff
    val g = (color shr 8) and 0x0000ff
    val b = color and 0x0000FF

    val sb = StringBuilder()
    sb.append("#")
        .append(int2Hex((a shr 4) and 0x0f))
        .append(int2Hex(a and 0x0f))
        .append(int2Hex((r shr 4) and 0x0f))
        .append(int2Hex(r and 0x0f))
        .append(int2Hex((g shr 4) and 0x0f))
        .append(int2Hex(g and 0x0f))
        .append(int2Hex((b shr 4) and 0x0f))
        .append(int2Hex(b and 0x0f))
    return sb.toString()
}

fun int2Hex(value: Int): String {
    if(value > 15) {
        throw IllegalAccessException("数字不能大于15")
    }
    if(value < 10) {
        return value.toString()
    }
    when(value) {
        10 -> return "A"
        11 -> return "B"
        12 -> return "C"
        13 -> return "D"
        14 -> return "E"
        15 -> return "F"

    }
    return ""
}