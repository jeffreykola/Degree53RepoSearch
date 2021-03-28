package com.example.reposearch

import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reposearch.adapter.RepoRecyclerAdapter
import com.example.reposearch.controller.ApiHandler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.reposearch.fragments.ReadmeDisplayFragment
import com.example.reposearch.models.Item
import com.example.reposearch.models.ReadmeInfo
import kotlinx.android.synthetic.main.fragment_view_results.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


class ViewResultsFragment : Fragment(), RepoRecyclerAdapter.ItemClickListener{
    // TODO: Rename and change types of parameters

    private lateinit var repoAdapter: RepoRecyclerAdapter;
    private var requestHandler: ApiHandler = ApiHandler()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        repoAdapter = RepoRecyclerAdapter(this)
        return inflater.inflate(R.layout.fragment_view_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var bundle: Bundle? = this.arguments
        var items: ArrayList<Item> = bundle?.getParcelableArrayList<Item>("responses") as ArrayList<Item>
        repoAdapter.setListItems(items)

        initRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView(){
        recycler_view.layoutManager = LinearLayoutManager(context!!)

        recycler_view.adapter = repoAdapter
    }

    override fun onButtonClick(item: Item) {
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<ReadmeInfo> = ApiHandler.retrofit.getRepoReadme(item.owner.login, item.name)
            if(response.isSuccessful) {
                val bundle: Bundle = Bundle()
                bundle.putString("base64content", response.body()!!.content)

                val readmeDisplayFragment : Fragment = ReadmeDisplayFragment()
                readmeDisplayFragment.arguments = bundle

                addFragment(readmeDisplayFragment)
            }
        }

    }

    private fun addFragment(readmeDisplayFragment: Fragment){
        val fragmentTransaction: FragmentTransaction =  fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.defaultFragment, readmeDisplayFragment)
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss()

    }


}