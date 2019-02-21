package com.keanequibilan.dailynasaviewer.ui.presenter

import com.keanequibilan.dailynasaviewer.isVerySuccessful
import com.keanequibilan.dailynasaviewer.model.Apod
import com.keanequibilan.dailynasaviewer.ui.view.MainView
import retrofit2.Response

/**
 * Controls the [MainView].
 */
class MainPresenter(private val view: MainView) {

    /**
     * Call this when a [Response] with an [Apod] is loaded. This function will invoke a [view] method depending on the
     * success of the response.
     */
    fun onLoadApod(response: Response<Apod>) {
        response.body()?.let { apod ->
            if (response.isVerySuccessful()) {
                view.showSuccess(apod)
            } else {
                view.showError()
            }
        }
    }
}