package com.example.reposearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRepo {
    @GET("/search/repositories")
    fun searchRepositories(@Query("q") name: String) : Call<List<Repository>>
}