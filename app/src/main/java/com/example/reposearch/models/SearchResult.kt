package com.example.reposearch.models

import android.os.Parcelable
import com.example.reposearch.models.Item
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

// @SuppressLint("ParcelCreator")]
@JsonClass(generateAdapter = true)
@Parcelize
data class SearchResult(
        val incomplete_results: Boolean,
        val items: List<Item>,
        val total_count: Int
): Parcelable