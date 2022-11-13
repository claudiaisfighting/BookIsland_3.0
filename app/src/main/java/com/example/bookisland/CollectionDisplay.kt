package com.example.bookisland

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CollectionDisplay : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookAdapter: CollectionDisplayRecycleAdapter
    private lateinit var collectionName: TextView
    private lateinit var removeCollectionButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_display)
        collectionName = findViewById(R.id.collectionName)
        removeCollectionButton = findViewById(R.id.removeCollectionButton)

        val collection = intent.getSerializableExtra("Collection") as BookCollection

        collectionName.text = collection.name
        recyclerView = findViewById(R.id.collection_display)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        bookAdapter = CollectionDisplayRecycleAdapter(this,collection.bookList!!)
        recyclerView.adapter = bookAdapter

        removeCollectionButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                (application as CollectionApplication).db.CollectionsDao().delete(collection.name!!)
                }
            finish()
            }
        }
    }