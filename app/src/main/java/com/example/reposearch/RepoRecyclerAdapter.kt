package com.example.reposearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_repo_list_item.view.*

class RepoRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Item> = ArrayList()

    fun setListItems(items: List<Item>){
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_repo_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(holder){
           is RepoViewHolder ->{
               val item = items.get(position)
               holder.bind(item)
           }
       }
    }

    private fun listener(moreInfo: Button) {
        // TODO: Get URL
        // TODO: Send URL to MainActivity
        // TODO: Make request in MainActivity
        // TODO: Create a new Fragment in from new with all information
        // TODO: Display informaiton
    }


    override fun getItemCount(): Int {
        return items.size
    }



     class RepoViewHolder constructor( itemView: View) : RecyclerView.ViewHolder(itemView){
        val repo_name: TextView = itemView.repo_name_value
        val stars: TextView = itemView.repo_stars
        val forks: TextView = itemView.repo_forks
        val more_info: Button = itemView.get_more_details


         fun bind(repoItem: Item){
             repo_name.setText(repoItem.name)
             stars.setText(repoItem.stargazers_count.toString())
             forks.setText(repoItem.forks.toString())
             more_info.setTag("/repos/${repoItem.owner}/${repoItem.name}/readme")
         }
    }




}