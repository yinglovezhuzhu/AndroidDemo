package com.owen.demo.android.anim

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.owen.demo.android.R

/**
 *
 * <br/>Author：yunying.zhang
 * <br/>Email: yunyingzhang@rastar.com
 * <br/>Date: 2020/10/21
 */

class DynamicAnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dynamic_anim)

        findViewById<View>(R.id.imageView).apply {
            var y = 0f
            var dy: Float
            var startValue = 0f
            // 添加手势，滑动时向下移动对象，当手放开时，向上采用弹簧动画恢复到原位
            setOnTouchListener { v, event ->
                when(event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startValue = 0f
                        y = event.rawY
                    }

                    MotionEvent.ACTION_MOVE -> {
                        dy = event.rawY - y
                        y = event.rawY
                        v.y = v.y + dy
                        startValue += dy
                    }
                    MotionEvent.ACTION_UP -> {
                        SpringAnimation(v, DynamicAnimation.TRANSLATION_Y, 0f).apply {
                            setStartValue(startValue)
                            setStartVelocity(0f)
                            // 设置阻尼比
//                            spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                            spring.dampingRatio = 0f
                            // 设置强度
                            spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
                            start()
                        }
                    }
                }
                true
            }
        }



    }


}