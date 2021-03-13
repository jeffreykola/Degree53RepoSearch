package com.example.reposearch

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.ArrayList
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var fragmentManager: FragmentManager
    lateinit var coroutineJob: Job



    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

//    val adapter = moshi.adapter<Any>(Object::class.java)




    val retrofit: SearchRepo = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SearchRepo::class.java);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fragmentManager = supportFragmentManager
        coroutineJob = Job()
        handleIntent(intent)
    }



    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }


    private fun handleIntent(intent:Intent){
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)

            // TODO: Make search request
            GlobalScope.launch(Dispatchers.IO) {
                val responses = retrofit.searchRepositories(query!!)
                if(responses.isSuccessful){
                    // TODO: Send message to fragment
                    val bundle: Bundle = Bundle()
                    bundle.putParcelableArrayList("responses", responses.body()?.items as ArrayList<out Parcelable>)
                    val resultsViewFragement: Fragment = ViewResults()
                    resultsViewFragement.arguments = bundle

                    replaceWithViewResultsFragment(resultsViewFragement)

                    }else{
                        var status: TextView = findViewById(R.id.searchHint);
                        status.text = "Could not complete search, please try again.."
                    }

                    }
                }

            }


    private fun replaceWithViewResultsFragment(resultsFragment: Fragment){
        val fragmentTransaction: FragmentTransaction =  fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.defaultFragment, resultsFragment)
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)


        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu!!.findItem(R.id.search).actionView as android.widget.SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true;
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + coroutineJob


}