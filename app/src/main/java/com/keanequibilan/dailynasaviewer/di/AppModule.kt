package com.keanequibilan.dailynasaviewer.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.keanequibilan.dailynasaviewer.network.RetrofitClient
import com.keanequibilan.dailynasaviewer.viewmodel.ApodViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The module used to provide dependencies throughout the application. Normally, this would be split up into different
 * modules, but since this is a sample app, I'm putting everything in here.
 */
val APP_MODULE = module {
    /**
     * Provides the [RetrofitClient]. Depends on [OkHttpClient], [CoroutineCallAdapterFactory],
     * and [GsonConverterFactory].
     */
    single { RetrofitClient(get(), get(), get()) }

    /**
     * Provides the [OkHttpClient].
     */
    single { OkHttpClient() }

    /**
     * Provides the [CoroutineCallAdapterFactory].
     */
    single { CoroutineCallAdapterFactory() }

    /**
     * Provides the [GsonConverterFactory].
     */
    single { GsonConverterFactory.create() }

    /**
     * Provides a [kotlin.coroutines.CoroutineContext] specifically for IO tasks.
     */
    single("backgroundContext") { Dispatchers.IO }

    /**
     * Provides the [ApodViewModel]. Depends on [RetrofitClient] and a background [kotlin.coroutines.CoroutineContext].
     */
    viewModel { ApodViewModel(get(), get("backgroundContext")) }
}