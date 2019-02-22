package com.keanequibilan.dailynasaviewer.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.keanequibilan.dailynasaviewer.R
import com.keanequibilan.dailynasaviewer.model.Apod
import com.keanequibilan.dailynasaviewer.ui.presenter.MainPresenter
import com.keanequibilan.dailynasaviewer.ui.view.MainView
import com.keanequibilan.dailynasaviewer.viewmodel.ApodViewModel
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

    override fun showSuccess(apod: Apod) {
        Toast.makeText(this, "Success! ${apod.title}", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
    }
}
