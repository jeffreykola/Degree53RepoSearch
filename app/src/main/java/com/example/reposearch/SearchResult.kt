package com.example.reposearch

import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

// @SuppressLint("ParcelCreator")]
@JsonClass(generateAdapter = true)
@Parcelize
data class SearchResult(
    val incomplete_results: Boolean,
    val items: List<Item> ,
    val total_count: Int
): Parcelable