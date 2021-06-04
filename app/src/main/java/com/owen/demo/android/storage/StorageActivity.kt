package com.owen.demo.android.storage

import android.app.Activity
import android.app.usage.StorageStatsManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.storage.StorageManager
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.owen.demo.android.R
import java.io.File
import java.net.URI
import java.text.DecimalFormat
import java.text.NumberFormat

class StorageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        val tvMsg = findViewById<TextView>(R.id.tvMsg)



        val storageManager = getSystemService(Context.STORAGE_SERVICE) as StorageManager
        val primaryStorageVolume = storageManager.primaryStorageVolume

        val storageStateManager = getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager

        tvMsg.append("设备主存储：\n")
        tvMsg.append("状态：${primaryStorageVolume.state}\n")
        tvMsg.append("UUID：${primaryStorageVolume.uuid}\n")
        tvMsg.append("isEmulated：${primaryStorageVolume.isEmulated}\n")
        tvMsg.append("isRemovable：${primaryStorageVolume.isRemovable}\n")

        tvMsg.append("\n")


        val externalFileDir = getExternalFilesDir(null)
        if(null != externalFileDir) {
            val uuid = storageManager.getUuidForPath(externalFileDir);
            tvMsg.append("应用外部存储目录：${formatStorageSize(storageStateManager.getFreeBytes(uuid))} / ${formatStorageSize(storageStateManager.getTotalBytes(uuid))}\n")
            tvMsg.append("UUID：$uuid\n")
            tvMsg.append("可用空间：${formatStorageSize(storageManager.getAllocatableBytes(uuid))}")
        }

        findViewById<Button>(R.id.btn_open_directory).setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).apply {
                // Optionally, specify a URI for the directory that should be opened in
                // the system file picker when it loads.
                putExtra(DocumentsContract.EXTRA_INITIAL_URI, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            }

            startActivityForResult(intent, 1000)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(Activity.RESULT_OK == resultCode && 1000 == requestCode) {
            data?.data?.also {
                Log.e("AAAAA", "Chose uri: ${it}")


            }
        }
    }


    fun queryMediaImage() {
        val projection =  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.RELATIVE_PATH,
        ) else arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA
        )

        val selection = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) "${MediaStore.Images.Media.RELATIVE_PATH} not like ?" else "${MediaStore.Images.Media.DATA} no like ?"
        val selectionArgs = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) arrayOf("com.%") else arrayOf("%/com.%")
    //        val projection =  arrayOf(
    //            MediaStore.Images.Media._ID,
    //            MediaStore.Images.Media.DISPLAY_NAME,
    //            MediaStore.Images.Media.DATA
    //        )
    //        val selection = "${MediaStore.Images.Media.DATA} not like ?"
    //        val selectionArgs = arrayOf("%/com.%")



        contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
            selection, selectionArgs, "${MediaStore.Images.Media.DATE_ADDED} ASC")?.use { cursor ->

            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val displayNameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val relativePathColumn = cursor.getColumnIndex(MediaStore.Images.Media.RELATIVE_PATH)
                while(cursor.moveToNext()) {
                    println("ID = ${cursor.getLong(idColumn)}, RELATIVE_PATH = ${cursor.getString(relativePathColumn)}, DISPLAY_NAME = ${cursor.getString(displayNameColumn)}")
                }
            } else {
                val dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                while(cursor.moveToNext()) {
                    println("ID = ${cursor.getLong(idColumn)}, DATA = ${cursor.getString(dataColumn)}, DISPLAY_NAME = ${cursor.getString(displayNameColumn)}")
                }
            }
        }
    }

}




fun formatStorageSize(bytes: Long): String {
    if(bytes < 100) {
        return "$bytes B"
    }

    var space = bytes.div(1000.0)
    if(space < 1000) {
        return "${String.format("%.2f", space)} KB"
    }

    space = space.div(1000.0)
    if(space < 1000) {
        return "${String.format("%.2f", space)} MB"
    }

    space = space.div(1000.0)
    if(space < 1000) {
        return "${String.format("%.2f", space)} GB"
    }

    return "${String.format("%.2f", space.div(1000.0))}} TB"
}