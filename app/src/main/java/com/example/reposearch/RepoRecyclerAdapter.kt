package com.example.reposearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_repo_list_item.view.*

class RepoRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
               holder.bind(items.get(position))
           }
       }
    }

    override fun getItemCount(): Int {
        return items.size
    }



     class RepoViewHolder constructor( itemView: View) : RecyclerView.ViewHolder(itemView){
        val repo_name = itemView.repo_name_value
        val stars = itemView.repo_stars
        val forks = itemView.repo_forks


         fun bind(repoItem: Item){
             repo_name.setText(repoItem.name)
             stars.setText(repoItem.stargazers_count.toString())
             forks.setText(repoItem.forks.toString())
         }
    }

}