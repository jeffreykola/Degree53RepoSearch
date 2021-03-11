package com.example.reposearch

data class SearchResult(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)