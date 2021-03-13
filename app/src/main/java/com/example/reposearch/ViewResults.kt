package com.example.reposearch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_view_results.*
import kotlinx.coroutines.currentCoroutineContext


class ViewResults : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var repoAdapter: RepoRecyclerAdapter;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        repoAdapter = RepoRecyclerAdapter()
        return inflater.inflate(R.layout.fragment_view_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var bundle: Bundle? = this.arguments
        Log.d("BUNDLE RECEIVED", this.arguments.toString())
        var items: ArrayList<Item> = bundle?.getParcelableArrayList<Item>("responses") as ArrayList<Item>
        repoAdapter.setListItems(items)
        initRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView(){
        recycler_view.layoutManager = LinearLayoutManager(context!!)

        recycler_view.adapter = repoAdapter
    }


}