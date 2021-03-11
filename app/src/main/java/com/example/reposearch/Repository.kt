package com.example.reposearch

data class RepositoryInfo(val open_issues: Int, val forks_count: Int){


    override fun toString(): String {
        return "issues:  ${open_issues}, forks: $forks_count"
    }
}
