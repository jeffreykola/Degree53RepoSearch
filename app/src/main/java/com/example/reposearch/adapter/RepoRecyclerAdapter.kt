package com.example.reposearch.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reposearch.R
import com.example.reposearch.models.Item
import kotlinx.android.synthetic.main.layout_repo_list_item.view.*

class RepoRecyclerAdapter(private var listener: ItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
               holder.itemView.setOnClickListener{ listener.onButtonClick(item)}
           }
       }
    }


    override fun getItemCount(): Int {
        return items.size
    }



     class RepoViewHolder constructor( itemView: View) : RecyclerView.ViewHolder(itemView){
        val repo_name: TextView = itemView.repo_name_value
        val stars: TextView = itemView.repo_stars
        val forks: TextView = itemView.repo_forks

         fun bind(repoItem: Item){
             repo_name.setText(repoItem.name)
             stars.setText(repoItem.stargazers_count.toString())
             forks.setText(repoItem.forks.toString())
         }
    }


    interface ItemClickListener{
        fun onButtonClick(item: Item)
    }




}