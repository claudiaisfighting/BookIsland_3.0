package com.example.bookisland

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddOrRemoveBookAdapter(private val context: Context, private val collections: List<DisplayCollection>,
                             private val book: Book, private val activity : Activity)
    : RecyclerView.Adapter<AddOrRemoveBookAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.add_or_remove_book_collections, parent, false)

        return ViewHolder(view)
    }
    override fun getItemCount() = collections.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val name = itemView.findViewById<TextView>(R.id.collection_name_add_or_remove)
        private var addButton = itemView.findViewById<Button>(R.id.button)
        private var removeButton = itemView.findViewById<Button>(R.id.removeFromCollection)
        @OptIn(DelicateCoroutinesApi::class)
        fun bind(collection: DisplayCollection) {
            name.text = collection.name
            addButton.setOnClickListener {
                GlobalScope.launch {
                    val Collection = (activity.application as CollectionApplication).db.CollectionsDao().getCollection(collection.name!!)
                    Collection.bookList.add(convertToBookEntity(book))
                    (activity.application as CollectionApplication).db.CollectionsDao().deleteCollection(Collection)
                    (activity.application as CollectionApplication).db.CollectionsDao().insert(Collection)
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Added to Collection", Toast.LENGTH_SHORT).show()
                    }
                    addButton.isClickable = false
                }
            }

            removeButton.setOnClickListener {
                GlobalScope.launch {
                    val Collection = (activity.application as CollectionApplication).db.CollectionsDao().getCollection(collection.name!!)
                    collection.collection.remove(convertToBookEntity(book))
                    val collectionEntity = CollectionEntity(collection.name!!,collection.collection)
                    (activity.application as CollectionApplication).db.CollectionsDao().deleteCollection(Collection)
                    (activity.application as CollectionApplication).db.CollectionsDao().insert(collectionEntity)
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(context, "Removed from Collection", Toast.LENGTH_SHORT).show()
                        }
                    removeButton.isClickable = false
                    /**(activity.application as CollectionApplication).db.CollectionsDao().deleteCollection(Collection)
                    collection.collection.remove(convertToBookEntity(book))
                    Collection.bookList = collection.collection
                    (activity.application as CollectionApplication).db.CollectionsDao().insert(Collection)**/
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val collections= collections[position]
        holder.bind(collections)
    }

    fun convertToBookEntity(book: Book): BookEntity {
        return BookEntity(
            title = book.title!!,
            authors = book.authors!!,
            description = book.description!!,
            thumbnail = book.thumbnail!!,
            saleability = book.saleability!!,
            //price = book.price!!
        )
    }
}
