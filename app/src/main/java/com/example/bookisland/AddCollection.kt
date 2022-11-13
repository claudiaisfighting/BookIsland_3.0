package com.example.bookisland

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCollection : AppCompatActivity() {
    private lateinit var collectionName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_collection)
        collectionName = findViewById(R.id.CollectionNameInput)

        findViewById<Button>(R.id.add).setOnClickListener {
            val bookList : MutableList<BookEntity> = mutableListOf()
            val book_collection = CollectionEntity(collectionName.text.toString(),bookList)
            lifecycleScope.launch(Dispatchers.IO) {
                (application as CollectionApplication).db.CollectionsDao().insert(book_collection)
                }
            finish()
            }
            }

        }