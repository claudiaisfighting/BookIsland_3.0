package com.example.bookisland

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CollectionDisplayRecycleAdapter (private val context: Context, private val collectionBooks: MutableList<Book>)
        : RecyclerView.Adapter<CollectionDisplayRecycleAdapter.ViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.search_display_item, parent, false)

            return ViewHolder(view)
        }

        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView), View.OnClickListener
        {
            val name : TextView = mView.findViewById(R.id.name)
            var BookImage : ImageView = mView.findViewById(R.id.BookImage1)

            init {
                itemView.setOnClickListener(this)
            }

            fun bind(book: Book) {
                name.text = book.title

                //adding an 's' to make it HTTPS since HTTP does not work
                book.thumbnail = book.thumbnail?.subSequence(0,4).toString() + "s" + book.thumbnail?.subSequence(4, book.thumbnail!!.length)
                    .toString()
                Glide.with(context)
                    .load(book.thumbnail)
                    .centerInside()
                    .into(BookImage)
            }
            override fun onClick(v: View?) {
                // TODO: Get selected article
                val book = collectionBooks[absoluteAdapterPosition]
                // TODO: Navigate to Details screen and pass selected article
                val intent = Intent(context, BookDetail::class.java)
                val bundle = Bundle()
                bundle.putSerializable("value", book)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val book = collectionBooks[position]
            holder.bind(book)
        }

        override fun getItemCount(): Int {
            return collectionBooks.size
        }

    }
