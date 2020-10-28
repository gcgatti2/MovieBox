package com.backbase.assignment.ui.data.remote.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    @Json(name = "name")
    val name: String
): Parcelable