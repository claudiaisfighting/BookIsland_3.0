package com.example.bookisland

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.launch


class CollectionsFragment : Fragment() {
    private val collectionsList = mutableListOf<DisplayCollection>()
    private lateinit var collectionsRecyclerView: RecyclerView
    private lateinit var collectionsAdapter: CollectionsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_collections, container, false)
        val layoutManager = LinearLayoutManager(context)
        collectionsRecyclerView = view.findViewById(R.id.collections_recycler)
        collectionsRecyclerView.layoutManager = layoutManager
        collectionsRecyclerView.setHasFixedSize(true)
        collectionsAdapter = CollectionsAdapter(view.context, collectionsList)
        collectionsRecyclerView.adapter = collectionsAdapter

        view.findViewById<Button>(R.id.AddCollection).setOnClickListener {
            val i = Intent(view.context, AddCollection::class.java)
            startActivity(i)
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchCollectionsList()
    }

    private fun fetchCollectionsList()
    {
        lifecycleScope.launch {
            (activity?.application as CollectionApplication).db.CollectionsDao().getCollections().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayCollection(
                        entity.name,
                        entity.bookList,
                    )
                }.also { mappedList ->
                    collectionsList.clear()
                    collectionsList.addAll(mappedList)
                    collectionsAdapter.notifyDataSetChanged()
                }
            }
        }
    }


    companion object {
        fun newInstance(): CollectionsFragment {
            return CollectionsFragment()
        }
    }
}

