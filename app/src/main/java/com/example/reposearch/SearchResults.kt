package com.example.reposearch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

class SearchResults : Fragment() {
     val retrofit: SearchRepo = Retrofit.Builder()
        .baseUrl(" https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(SearchRepo::class.java);

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getSearchRepo();
        return inflater.inflate(R.layout.fragment_search_results, container, false)
    }


    fun getSearchRepo(){
        GlobalScope.launch(Dispatchers.IO){
            val response: Response<List<Repository>> = retrofit.searchRepositories("jQuery").awaitResponse();
            if(response.isSuccessful) {
                val data: List<Repository> = response.body()!!
                for(d in data){
                   Log.d("Received..", d.toString());
                }
            }
        }
    }

}

