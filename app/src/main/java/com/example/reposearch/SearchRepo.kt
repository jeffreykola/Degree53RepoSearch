package com.example.reposearch

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchRepo {
    @GET("/search/repositories")
    suspend fun searchRepositories(@Query("q") name: String) : Response<SearchResult>
    @GET("/repos/{owner}/{repo}/readme")
    suspend fun getRepoReadme(@Path("owner") owner: String, @Path("repo") name: String) : Response<ReadmeInfo>

}