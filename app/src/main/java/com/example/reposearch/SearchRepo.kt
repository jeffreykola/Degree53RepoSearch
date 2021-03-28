package com.example.reposearch

import com.example.reposearch.models.ReadmeInfo
import com.example.reposearch.models.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchRepo {
    @GET("/search/repositories")
    suspend fun searchRepositories(@Query("q") name: String) : Response<SearchResult>
    @GET("/repos/{owner}/{repo}/readme")
    suspend fun getRepoReadme(@Path("owner") owner_name: String, @Path("repo") name: String) : Response<ReadmeInfo>

}