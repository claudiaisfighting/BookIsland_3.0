package com.example.bookisland

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ParentAdapterSearch(private val context: Context,
                          private val categories: List<String>,
                          private val bookcategories: List<List<Book>>,
) : RecyclerView.Adapter<ParentAdapterSearch.ParentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ParentViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.parent_recycler_view_searchfragment, parent, false)
        return ParentViewHolder(view)
    }

    inner class ParentViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
    {
        val title : TextView = mView.findViewById(R.id.parent_item_title)
        var recycler : RecyclerView = mView.findViewById(R.id.child_recyclerview)
        private lateinit var adapter: ChildAdapterSearch
        fun bind(bookCategory: List<Book>, category: String) {
            title.text = category
            recycler.layoutManager = LinearLayoutManager(mView.context,LinearLayoutManager.HORIZONTAL,false)
            adapter= ChildAdapterSearch(context,bookCategory)
            recycler.adapter = adapter
        }
    }


    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val bookCategory = bookcategories[position]
        val category = categories[position]
        holder.bind(bookCategory, category )
    }

    override fun getItemCount(): Int {
        return bookcategories.size
    }
}