package com.owen.demo.android

import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * <br/>Author：yunying.zhang
 * <br/>Email: yunyingzhang@rastar.com
 * <br/>Date: 2020/9/3
 */
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.tvTitle)
    val desc: TextView = itemView.findViewById(R.id.tvDesc)
    val check: RadioButton = itemView.findViewById(R.id.ibtnCheck)


}