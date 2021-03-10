package com.owen.demo.android.cview

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.owen.demo.android.R

/**
 *
 * <br/>Author：yunying.zhang
 * <br/>Email: yunyingzhang@rastar.com
 * <br/>Date: 2020/9/10
 */
public class PieChartView : View {

    var paint: Paint = Paint()
    var rectF: RectF = RectF()

    var outRect = Rect()

    @ColorInt
    var color: Int = 0xffffff
    var radius: Int = 0

    var paintRect = RectF()

    constructor(context: Context) : this(context, null) {

    }

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0) {
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init(attributeSet, defStyleAttr, 0)
    }

    @TargetApi(21)
    constructor(
        context: Context,
        attributeSet: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attributeSet, defStyleAttr, defStyleRes) {
        init(attributeSet, defStyleAttr, defStyleRes)

    }

    private fun init(attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
//        context.obtainStyledAttributes(attributeSet, R.styleable.PieChartView, defStyleAttr, defStyleRes).apply {
//            try {
//                color = getColor(R.styleable.PieChartView_color, 0)
//                radius = getInt(R.styleable.PieChartView_radius, 50)
//            } finally {
//                recycle()
//            }
//        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = color

        getGlobalVisibleRect(outRect)

        val width = outRect.right - outRect.left
        val height = outRect.bottom - outRect.top

        radius = width.coerceAtMost(height) / 2 - 80

        // 计算绘制区域
        rectF.set(width / 2.0f - radius, height / 2.0f - radius, width / 2.0f + radius, height / 2.0f + radius)

        // 绘制第一块
        paint.color = Color.BLUE
        paintRect.set(rectF.left + 10, rectF.top + 10, rectF.right, rectF.bottom)
        canvas?.drawArc(paintRect, 0.0f, 120.0f, true, paint)

        // 绘制第二块
        paint.color = Color.CYAN
        paintRect.set(rectF.left, rectF.top, rectF.right - 10, rectF.bottom - 10)
        canvas?.drawArc(paintRect, 120.0f, 150.0f, true, paint)

        // 绘制第三块
        paintRect.set(rectF.left + 10, rectF.top, rectF.right, rectF.bottom - 10)
        paint.color = Color.RED
        canvas?.drawArc(paintRect, 270.0f, 90.0f, true, paint)

    }

}