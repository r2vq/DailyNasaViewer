package com.keanequibilan.dailynasaviewer.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.custom_fade_in, R.anim.custom_fade_out)
    }

    companion object {
        /**
         * Start the Full Screen Activity. You don't need to start the Activity with this method but it does expect that
         * certain requirements are fulfilled. This static method will ensure those are fulfilled without having to
         * figure out which is required.
         *
         * @param activity The source [Activity] required to start the new activity and create the [Intent].
         * @param url The [String] URL to load the image.
         */
        fun transitionToActivity(activity: Activity, url: String) {
            val intent = Intent(activity, FullScreenActivity::class.java)
                .apply {
                    putExtra(BUNDLE_KEY_URL, url)
                }
            activity
                .apply {
                    startActivity(intent)
                    overridePendingTransition(R.anim.custom_fade_in, R.anim.custom_fade_out)
                }
        }
    }
}
