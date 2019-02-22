package com.keanequibilan.dailynasaviewer

import retrofit2.Response

/**
 * Returns true only if the success code is 200 or 202.
 */
fun <T> Response<T>.isVerySuccessful() = code() == 200 || code() == 202