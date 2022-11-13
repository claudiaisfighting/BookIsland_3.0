package com.example.bookisland

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class CollectionsAdapter(private val context: Context, private val collections: List<DisplayCollection>)
    : RecyclerView.Adapter<CollectionsAdapter.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.collections_item, parent, false)
            return ViewHolder(view)
        }
        override fun getItemCount() = collections.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)//, View.OnClickListener
        {

            private val name = itemView.findViewById<TextView>(R.id.collection_name)
            private val books = itemView.findViewById<TextView>(R.id.books)
            private val button = itemView.findViewById<Button>(R.id.open_collection_button)
            fun bind(collection: DisplayCollection) {
                name.text = collection.name
                books.text = collection.collection.size.toString() + " Books"


                button.setOnClickListener {
                    val intent = Intent(context, CollectionDisplay::class.java)

                    val json: String = listToString(collection.collection)
                    val type = object : TypeToken<List<Book>>() {}.type
                    val result: List<Book> = parseArray<List<Book>>(json = json, typeToken = type)
                    val bookList = result as MutableList<Book>
                    val bookcollection = BookCollection(collection.name,bookList)
                    intent.putExtra("Collection", bookcollection)
                    context.startActivity(intent)
                }
            }

            /**override fun onClick(v: View?) {
                // TODO: Get selected article
                val collection = collections[absoluteAdapterPosition]
                // TODO: Navigate to Details screen and pass selected article
                val intent = Intent(context, CollectionDisplay::class.java)
                intent.putExtra("Collection", collection)
                context.startActivity(intent)
            }**/
        }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val collections= collections[position]
        holder.bind(collections)
    }

    fun <T> listToString(list: List<T>?): String {
        val g = Gson()
        return g.toJson(list)
    }

    inline fun <reified T> parseArray(json: String, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson<T>(json, typeToken)
    }
    }

