package com.keanequibilan.dailynasaviewer.model

import com.google.gson.annotations.SerializedName
import com.keanequibilan.dailynasaviewer.*

/**
 * This model structures the APOD imagery and associated metadata. The parameter [copyright] is optional and is set to
 * null if the image is public domain.
 */
data class Apod(
    @SerializedName(APOD_FIELD_COPYRIGHT)
    val copyright: String?,

    @SerializedName(APOD_FIELD_DATE)
    val date: String,

    @SerializedName(APOD_FIELD_EXPLANATION)
    val explanation: String,

    @SerializedName(APOD_FIELD_HD_URL)
    val hdUrl: String,

    @SerializedName(APOD_FIELD_MEDIA_TYPE)
    val mediaType: String,

    @SerializedName(APOD_FIELD_SERVICE_VERSION)
    val serviceVersion: String,

    @SerializedName(APOD_FIELD_TITLE)
    val title: String,

    @SerializedName(APOD_FIELD_URL)
    val url: String
)