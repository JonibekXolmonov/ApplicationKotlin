package com.example.applicationkotlin

import android.content.*
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.drawToBitmap
import java.sql.Array
import java.util.ArrayList
import android.provider.MediaStore.Images
import androidx.core.content.ContentProviderCompat.requireContext
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var textView: TextView? = null
    var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val button1 = findViewById<Button>(R.id.bv1_text_share)
        button1.setOnClickListener(this)

        val button2 = findViewById<Button>(R.id.bv2_image_share)
        button2.setOnClickListener(this)

        val button3 = findViewById<Button>(R.id.bv3_image_text_share)
        button3.setOnClickListener(this)

        val button4 = findViewById<Button>(R.id.bv4_images_share)
        button4.setOnClickListener(this)

        val button5 = findViewById<Button>(R.id.bv5_image_view_share)
        button5.setOnClickListener(this)

        val button6 = findViewById<Button>(R.id.bv6_video_share)
        button6.setOnClickListener(this)

        val button7 = findViewById<Button>(R.id.bv7_link)
        button7.setOnClickListener(this)
    }


    override fun onClick(v: View) {

        textView = findViewById(R.id.tv_main_activity)

        val textToShare = textView?.text as String?
        var uriList = ArrayList<Uri>()
        val videoPath = Uri.parse("android.resource://${packageName}/raw/video_share")

        uriList = fillListWithUris(uriList)

        when (v.id) {
            R.id.bv1_text_share -> {
                shareTextWith(textToShare)
            }
            R.id.bv2_image_share -> {
                shareImageWith(uriList[2])
            }
            R.id.bv3_image_text_share -> {
                shareTextAndImage(textToShare, uriList[2])
            }
            R.id.bv4_images_share -> {
                shareMultiImage(uriList)
            }
            R.id.bv5_image_view_share -> {
                shareImageView()
            }
            R.id.bv6_video_share -> {
                shareVideo(videoPath)
            }
            R.id.bv7_link -> {
                openBrowser()
            }
            else -> {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun shareTextWith(text: String?) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Share"))
    }

    private fun shareImageWith(uriToImage: Uri?) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_STREAM, uriToImage)
        intent.type = "image/*"
        startActivity(Intent.createChooser(intent, "Share"))
    }

    private fun shareTextAndImage(text: String?, uri: Uri?) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.type = "image/*"
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(intent, "Share"))
    }

    private fun shareMultiImage(uris: ArrayList<Uri>) {
        val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris)
        intent.type = "image/*"
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        try {
            startActivity(Intent.createChooser(intent, "Share Via:"))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No App Available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareImageView() {
        imageView = findViewById(R.id.image_view_id)
        imageView?.buildDrawingCache()
        val image: Bitmap? = imageView?.drawingCache

        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/*"
        share.putExtra(Intent.EXTRA_STREAM, getImageUri(this, image!!))
        startActivity(Intent.createChooser(share, "Share via"))
    }

    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    private fun shareVideo(path: Uri) {
        val intent = Intent(Intent.ACTION_SEND)

        intent.apply {
            type = "video/*"
            putExtra(Intent.EXTRA_STREAM, path)
        }

        startActivity(Intent.createChooser(intent, "Sharing Video:"))
    }

    private fun openBrowser() {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }

    private fun fillListWithUris(uriList: ArrayList<Uri>): ArrayList<Uri> {
        uriList.add(Uri.parse("android.resource://${packageName}/drawable/image_1"))
        uriList.add(Uri.parse("android.resource://${packageName}/drawable/image_2"))
        uriList.add(Uri.parse("android.resource://${packageName}/drawable/image_3"))
        uriList.add(Uri.parse("android.resource://${packageName}/drawable/im_twitter_logo"))

        return uriList
    }
}