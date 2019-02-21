package com.keanequibilan.dailynasaviewer.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.keanequibilan.dailynasaviewer.NASA_API_BASE_URL
import com.keanequibilan.dailynasaviewer.NASA_API_KEY
import com.keanequibilan.dailynasaviewer.model.Apod
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(
    okHttpClient: OkHttpClient,
    coroutineCallAdapterFactory: CoroutineCallAdapterFactory,
    gsonConverterFactory: GsonConverterFactory
) {

    private val client: NasaService = Retrofit.Builder()
        .baseUrl(NASA_API_BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(coroutineCallAdapterFactory)
        .addConverterFactory(gsonConverterFactory)
        .build()
        .create(NasaService::class.java)

    /**
     * Retrieves the APOD from the NASA API. This call suspends the coroutine that is calling it.
     *
     * @see [NasaService.getApodAsync] for more info.
     *
     * @param date Optional. Default: today
     * @param isHd Optional. Default: false
     */
    suspend fun getApod(
        date: String? = null,
        isHd: Boolean? = null
    ): Response<Apod> = client
        .getApodAsync(date, isHd, apiKey = NASA_API_KEY)
        .await()
}