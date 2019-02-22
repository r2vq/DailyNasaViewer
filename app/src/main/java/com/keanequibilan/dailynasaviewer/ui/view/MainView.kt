package com.keanequibilan.dailynasaviewer.ui.view

/**
 * The view that interacts with [com.keanequibilan.dailynasaviewer.ui.presenter.MainPresenter] and controls the
 * [com.keanequibilan.dailynasaviewer.ui.activity.MainActivity].
 */
interface MainView {
    /**
     * Load the image in the image view.
     *
     * @param url The URL of the image to load.
     */
    fun loadImage(url: String)

    /**
     * Load the text of the image.
     */
    fun loadTitle(title: String)

    /**
     * Invoke when you want to show the error state in the main view.
     *
     * @param code The response code from the server
     */
    fun showError(code: Int)
}