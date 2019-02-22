package com.keanequibilan.dailynasaviewer.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.keanequibilan.dailynasaviewer.R
import kotlinx.android.synthetic.main.activity_full_screen.*

private const val BUNDLE_KEY_URL = "com.keanequibilan.fullscreenactivity.url"

class FullScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        intent
            .getStringExtra(BUNDLE_KEY_URL)
            ?.let { url ->
                Glide
                    .with(this)
                    .load(url)
                    .centerCrop()
                    .into(iv_apod)
            }

        iv_apod.setOnClickListener { finish() }
    }

    companion object {
        /**
         * Start the Full Screen Activity. You don't need to start the Activity with this method but it does expect that
         * certain requirements are fulfilled. This static method will ensure those are fulfilled without having to
         * figure out which is required.
         *
         * @param context The [Context] required to start the activity and create the [Intent].
         * @param url The [String] URL to load the image.
         */
        fun transitionToActivity(context: Context, url: String) {
            val intent = Intent(context, FullScreenActivity::class.java)
                .apply {
                    putExtra(BUNDLE_KEY_URL, url)
                }
            context.startActivity(intent)
        }
    }
}
