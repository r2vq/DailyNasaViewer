package com.keanequibilan.dailynasaviewer.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.keanequibilan.dailynasaviewer.R
import com.keanequibilan.dailynasaviewer.ui.presenter.MainPresenter
import com.keanequibilan.dailynasaviewer.ui.view.MainView
import com.keanequibilan.dailynasaviewer.viewmodel.ApodViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: MainPresenter by inject { parametersOf(this) }
    private val apodViewModel: ApodViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apodViewModel
            .getApod()
            .observe(
                this,
                Observer { response -> presenter.onLoadApod(response) }
            )
    }

    override fun loadImage(url: String) {
        Glide
            .with(this)
            .load(url)
            .fitCenter()
            .into(iv_apod)
    }

    override fun loadTitle(title: String) {
        tv_title.text = title
    }

    override fun showError(code: Int) {
        tv_title.text = getString(R.string.error_message, code.toString())
    }
}
