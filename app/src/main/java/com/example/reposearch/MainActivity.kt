package com.example.reposearch

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.reposearch.controller.ApiHandler
import kotlinx.coroutines.*
import java.util.ArrayList
import kotlin.coroutines.CoroutineContext

class MainActivity: AppCompatActivity(), CoroutineScope{
    private lateinit var fragmentManager: FragmentManager
    lateinit var coroutineJob: Job

    val handler: ApiHandler = ApiHandler()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(">>> MainActivity->onCreate", "Activity Created")
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
                val responses = ApiHandler.retrofit.searchRepositories(query!!)
                if(responses.isSuccessful){
                    // TODO: Send message to fragment
                    val bundle: Bundle = Bundle()
                    bundle.putParcelableArrayList("responses", responses.body()?.items as ArrayList<out Parcelable>)

                    val resultsViewFragment: Fragment = ViewResultsFragment()
                    resultsViewFragment.arguments = bundle

                    replaceWithViewResultsFragment(resultsViewFragment)
                }

                //else{
//                        var status: TextView = findViewById(R.id.searchHint);
//                        status.text = "Could not complete search, please try again.."
                    //}

                    }
                }

            }


    private fun replaceWithViewResultsFragment(resultsFragment: Fragment){
        val fragmentTransaction: FragmentTransaction =  fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.defaultFragment, resultsFragment)
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss()
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