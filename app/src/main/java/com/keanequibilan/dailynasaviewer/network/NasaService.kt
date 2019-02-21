package com.keanequibilan.dailynasaviewer.network

import com.keanequibilan.dailynasaviewer.APOD_QUERY_PARAM_API_KEY
import com.keanequibilan.dailynasaviewer.APOD_QUERY_PARAM_DATE
import com.keanequibilan.dailynasaviewer.APOD_QUERY_PARAM_HD
import com.keanequibilan.dailynasaviewer.NASA_API_PATH_APOD
import com.keanequibilan.dailynasaviewer.model.Apod
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The [NasaService] defines all the used endpoints from the NASA Api. It was designed to be used with
 * `https://api.nasa.gov/` and is not guaranteed to work with other base URLs.
 */
interface NasaService {
    /**
     * This endpoint structures the Astronomy Picture of the Day imagery and associated metadata so that it can be
     * repurposed for other applications.
     *
     * @param date Optional. Default: today
     * @param isHd Optional. Default: false
     * @param apiKey Required. For the purposes of this application, this will be hard coded.
     */
    @GET(NASA_API_PATH_APOD)
    fun getApodAsync(
        @Query(APOD_QUERY_PARAM_DATE) date: String?,
        @Query(APOD_QUERY_PARAM_HD) isHd: Boolean?,
        @Query(APOD_QUERY_PARAM_API_KEY) apiKey: String
    ): Deferred<Apod>
}