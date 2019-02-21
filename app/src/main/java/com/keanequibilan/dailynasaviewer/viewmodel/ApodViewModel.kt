package com.keanequibilan.dailynasaviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keanequibilan.dailynasaviewer.model.Apod
import com.keanequibilan.dailynasaviewer.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

/**
 * A [ViewModel] that stores and manages [Apod] data. Retrieve the [Response] that holds the [Apod] with the [getApod]
 * method. It is a [LiveData] object so you can observe on it to receive the [Response] whenever a network call is made.
 */
class ApodViewModel(
    private val retrofitClient: RetrofitClient,
    private val backgroundContext: CoroutineContext
) : ViewModel() {
    private val apod: MutableLiveData<Response<Apod>> by lazy {
        MutableLiveData<Response<Apod>>().apply { loadApodResponse() }
    }

    /**
     * Returns a [LiveData] that notifies observers of updates posted to the wrapped [Apod] [Response].
     */
    fun getApod(): LiveData<Response<Apod>> = apod

    private fun MutableLiveData<Response<Apod>>.loadApodResponse() = CoroutineScope(backgroundContext).launch {
        postValue(retrofitClient.getApod())
    }
}