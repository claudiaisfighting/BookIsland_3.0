package com.example.bookisland

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddOrRemoveBook : AppCompatActivity() {
    private val collectionsList = mutableListOf<DisplayCollection>()
    private lateinit var collectionsRecyclerView: RecyclerView
    private lateinit var collectionsAdapter: AddOrRemoveBookAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_book_to_collection)
        fetchCollectionsList()
        val book = intent.getSerializableExtra("book") as Book
        val layoutManager = LinearLayoutManager(this)
        collectionsRecyclerView = findViewById(R.id.add_to_collections_recycler)
        collectionsRecyclerView.layoutManager = layoutManager
        collectionsRecyclerView.setHasFixedSize(true)
        collectionsAdapter = AddOrRemoveBookAdapter(this,collectionsList,book, activity = this@AddOrRemoveBook)
        collectionsRecyclerView.adapter = collectionsAdapter

    }

    private fun fetchCollectionsList()
    {
        lifecycleScope.launch {
            (application as CollectionApplication).db.CollectionsDao().getCollections().collect { databaseList ->
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
}