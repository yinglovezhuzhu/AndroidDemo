package com.owen.demo.android.pip

import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.owen.demo.android.R

/**
 *
 * <br/>Author：yunying.zhang
 * <br/>Email: yunyingzhang@rastar.com
 * <br/>Date: 2020/10/16
 */
class PIPActivity : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("PIP", "onCreate")

        setContentView(R.layout.activity_pip)

        actionBar?.hide()


        button = findViewById<Button>(R.id.btnPip).apply {
            setOnClickListener {
                val params = PictureInPictureParams.Builder()
                    .build()
                enterPictureInPictureMode(params)
                visibility = View.GONE
            }
        }
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if(isInPictureInPictureMode) {
            actionBar?.hide()
            findViewById<Button>(R.id.btnPip).apply {
//                visibility = View.GONE
            }
        } else {
            actionBar?.show()
            findViewById<Button>(R.id.btnPip).apply {
//                visibility = View.VISIBLE
            }
        }
    }
}