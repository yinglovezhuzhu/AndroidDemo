package com.owen.demo.android.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.owen.demo.android.R

/**
 *
 * <br/>Author：yunying.zhang
 * <br/>Email: yunyingzhang@rastar.com
 * <br/>Date: 2020/10/12
 */
class ClipboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_clipboard)

        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val etSource = findViewById<EditText>(R.id.etSource)
        val etDest = findViewById<EditText>(R.id.etDest)

        findViewById<Button>(R.id.btnCopy).setOnClickListener {
            if(etSource.text.isEmpty()) {
                Toast.makeText(this@ClipboardActivity, "复制的内容为空", Toast.LENGTH_SHORT).show()
            } else {
                val clipData = ClipData.newPlainText("copy text", etSource.text)
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(this@ClipboardActivity, "已复制到剪切板", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnParse).setOnClickListener {
            val text = clipboardManager.primaryClip?.getItemAt(0)?.text
            etDest.setText(text)
        }
    }
}