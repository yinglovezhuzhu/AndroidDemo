package com.owen.demo.android.anim

import android.animation.*
import android.graphics.PointF
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.owen.demo.android.R
import kotlin.math.sin

/**
 *
 * <br/>Author：yunying.zhang
 * <br/>Email: yunyingzhang@rastar.com
 * <br/>Date: 2020/10/21
 */

class AnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_anim)

        findViewById<ViewGroup>(R.id.container).apply {


            if(null == layoutTransition) {
                layoutTransition = LayoutTransition()
            }

//            layoutTransition.setAnimator(
//                LayoutTransition.DISAPPEARING, AnimatorInflater.loadAnimator(
//                    this@AnimActivity,
//                    R.animator.animator_view_disappearing
//                )
//            )
//            layoutTransition.setAnimator(
//                LayoutTransition.APPEARING, AnimatorInflater.loadAnimator(
//                    this@AnimActivity,
//                    R.animator.animator_view_appearing
//                )
//            )

            val pvhDisappearScaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f)
            val pvhDisappearScaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f)
            layoutTransition.setAnimator(LayoutTransition.DISAPPEARING,
                ObjectAnimator.ofPropertyValuesHolder(null as Any?, pvhDisappearScaleX, pvhDisappearScaleY)
                    .apply {
//                        interpolator = AnticipateInterpolator()
                    })




            val pvhAppearScaleX = PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f)
            val pvhAppearScaleY = PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f)
            layoutTransition.setAnimator(LayoutTransition.APPEARING,
                ObjectAnimator.ofPropertyValuesHolder(null as Any?, pvhAppearScaleX, pvhAppearScaleY)
                    .apply {
//                        interpolator = OvershootInterpolator()
                    })


            // "left" is just a placeholder; we'll put real properties/values in when needed
//            val pvhLeft = PropertyValuesHolder.ofInt("left", 0, 2)
//            val pvhTop = PropertyValuesHolder.ofInt("top", 0, 2)
//            val pvhRight = PropertyValuesHolder.ofInt("right", 0, 2)
//            val pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 2)
//            val pvhScrollX = PropertyValuesHolder.ofInt("scrollX", 0, 2)
//            val pvhScrollY = PropertyValuesHolder.ofInt("scrollY", 0, 2)
//            val anim = ObjectAnimator.ofPropertyValuesHolder(
//                null as Any?,
//                pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScrollX, pvhScrollY
//            ).apply {
////                interpolator = OvershootInterpolator()
//            }
//
//            layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, anim.clone())
//            layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, anim)
            layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, ObjectAnimator.ofPropertyValuesHolder(null as Any?, pvhAppearScaleX, pvhAppearScaleY))

        }


        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageOptBtn = findViewById<Button>(R.id.btnShow).apply {
            if(imageView.visibility == View.VISIBLE) {
                this.text = "隐藏"
            } else {
                this.text = "显示"
            }
            setOnClickListener {
                if(imageView.visibility == View.VISIBLE) {
                    imageView.visibility = View.GONE
                    this.text = "显示"
                } else {
                    imageView.visibility = View.VISIBLE
                    this.text = "隐藏"
                }
            }
        }



        findViewById<Button>(R.id.btnHide).apply {
            if(imageOptBtn.visibility == View.VISIBLE) {
                this.text = "隐藏操作按钮"
            } else {
                this.text = "显示操作按钮"
            }
            setOnClickListener {
                if(imageOptBtn.visibility == View.VISIBLE) {
                    this.text = "显示操作按钮"
                    imageOptBtn.visibility = View.GONE
                } else {
                    this.text = "隐藏操作按钮"
                    imageOptBtn.visibility = View.VISIBLE
                }

            }
        }

        findViewById<Button>(R.id.btnAnimator).apply {
            setOnClickListener { view ->
//                (AnimatorInflater.loadAnimator(this@LayoutAnimActivity, R.animator.animator_scale_alpha) as AnimatorSet).apply {
//                    setTarget(imageView)
//                    addListener(object: Animator.AnimatorListener {
//                        override fun onAnimationStart(animation: Animator?) {
//                        }
//
//                        override fun onAnimationEnd(animation: Animator?) {
//                            imageView.scaleX = 1.0f
//                            imageView.scaleY = 1.0f
//                            imageView.alpha = 1.0f
//                        }
//
//                        override fun onAnimationCancel(animation: Animator?) {
//                        }
//
//                        override fun onAnimationRepeat(animation: Animator?) {
//                        }
//
//                    })
//                    start()
//                }

//                // 定义X方向缩放动画
//                val scaleXAnim = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 2.0f).apply {
//                    duration = 1000
//                }
//
//                // 定义Y方向缩放动画
//                val scaleYAnim = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 2.0f).apply {
//                    duration = 1000
//                }
//
//                // 定义缩放AnimatorSet
//                val scaleAnimSet = AnimatorSet().apply {
//                    // 同时播放X缩放动画和Y缩放动画
//                    play(scaleXAnim).with(scaleYAnim)
//
//                }
//
//                // 定义透明度动画
//                val alphaAnim = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.0f).apply {
//                    duration = 1000
//                }
//
//                // 定义AnimatorSet
//                AnimatorSet().apply {
//                    // 先播放缩放AnimatorSet，接着播放透明度动画
//                    play(scaleAnimSet).before(alphaAnim)
//                    // 启动AnimatorSet
//                    start()
//                }




//                ValueAnimator.ofFloat(1.0f, 1.5f, 2.0f).apply {
//                    duration = 10000
//                    repeatMode = ValueAnimator.REVERSE
//                    repeatCount = ValueAnimator.INFINITE
//                    interpolator = AccelerateDecelerateInterpolator()
//                    addUpdateListener {
//                        // ValueAnimator 无法自动将更新的属性值设置到对象的属性中，只能在监听中实现对象属性值的设置。
//                        val value = it.animatedValue as Float
//                        imageView.scaleX = value
//                        imageView.scaleY = value
//                    }
//                    start()
//                }
//                ValueAnimator.ofFloat(1.0f, 2.0f, 1.5f, 1.2f, 1.0f).apply {
//                    duration = 1000
//                    repeatMode = ValueAnimator.RESTART
//                    repeatCount = ValueAnimator.INFINITE
//                    addUpdateListener {
//                        val value = it.animatedValue as Float
//                        imageView.scaleX = value
//                        imageView.scaleY = value
//                    }
//                    start()
//                }

//                val anim = ValueAnimator.ofObject(
//                    XYEvaluate(), PointF(1.0f, 1.0f), PointF(
//                        1.5f,
//                        2.0f
//                    )
//                ).apply {
//                    duration = 1000
//                    interpolator = SinInterpolator()
//
//                    addUpdateListener {
//                        Log.i(
//                            "AnimatorUpdate",
//                            "currentPlayTime = ${it.currentPlayTime} --> animatedFraction = ${it.animatedFraction}, animatedValue = ${it.animatedValue}"
//                        )
//                        val value = it.animatedValue as PointF
//                        imageView.scaleX = value.x
//                        imageView.scaleY = value.y
//                    }
//                    start()
//                }

                ObjectAnimator.ofObject(XYEvaluate(), PointF(1.0f, 1.0f), PointF(1.5f, 2.0f)).apply {
                    duration = 1000
                    repeatMode = ValueAnimator.REVERSE
                    repeatCount = ValueAnimator.INFINITE

                    addUpdateListener {
                        val value = it.animatedValue as PointF
                        imageView.scaleX = value.x
                        imageView.scaleY = value.y
                    }
                    start()
                }

                val pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.5f)
                val pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 2.0f)
                ObjectAnimator.ofPropertyValuesHolder(imageView, pvhScaleX, pvhScaleY)






//                (AnimatorInflater.loadAnimator(this@LayoutAnimActivity, R.animator.animator_scale) as ValueAnimator).apply {
//                    addUpdateListener {
//                        // ValueAnimator 无法自动将更新的属性值设置到对象的属性中，只能在监听中实现对象属性值的设置。
//                        val value = it.animatedValue as Float
//                        imageView.scaleX = value
//                        imageView.scaleY = value
//                    }
//                    start()
//                }
//                (AnimatorInflater.loadAnimator(this@AnimActivity, R.animator.animator_alpha) as ObjectAnimator).apply {
//                    target = imageView // ObjectAnimator 需要设置目标
//                    start()
//                }
            }
        }

//        findViewById<ImageView>(R.id.iv_loading).apply {
//            var animationDrawable = ResourcesCompat.getDrawable(resources, R.drawable.loading_animation, null) as AnimationDrawable
//            setImageDrawable(animationDrawable)
//            animationDrawable.start()
//
//        }

        findViewById<ImageView>(R.id.imageView).apply {
            val animationDrawable = ResourcesCompat.getDrawable(resources, R.drawable.animationvectordrawable, null) as AnimatedVectorDrawable
            setImageDrawable(animationDrawable)
            setOnClickListener {
                if(animationDrawable.isRunning) {
                    animationDrawable.reset()
                }
                animationDrawable.start()
            }
        }
    }

    class XYEvaluate : TypeEvaluator<PointF> {
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val x = startValue.x + fraction * (endValue.x  - startValue.x)
            val y = startValue.y + fraction * (endValue.y - startValue.y)
            return PointF(x, y)
        }
    }

    class SinInterpolator : TimeInterpolator {
        override fun getInterpolation(input: Float): Float {
            // 这里需要注意的是，三角函数sin()的参数的角度单位是弧度
            return sin((Math.PI / 2.0f) * input).toFloat()
        }
    }


}