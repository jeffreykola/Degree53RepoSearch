package com.example.reposearch

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class License(
    val key: String?="",
    val name: String?="No license",
    val node_id: String? ="",
    val spdx_id: String = "",
    val url: String?="NO url"
): Parcelable