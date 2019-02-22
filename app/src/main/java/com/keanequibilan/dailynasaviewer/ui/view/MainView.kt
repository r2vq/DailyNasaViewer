package com.keanequibilan.dailynasaviewer.ui.view

import com.keanequibilan.dailynasaviewer.model.Apod

interface MainView {
    /**
     * Invoke when you to show the success state in the main view.
     */
    fun showSuccess(apod: Apod)

    /**
     * Invoke when you want to show the error state in the main view.
     */
    fun showError()
}