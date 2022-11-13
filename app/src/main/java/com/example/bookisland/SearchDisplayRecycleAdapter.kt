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


class SearchDisplayRecycleAdapter(private val context: Context, private val books: List<Book>)
    : RecyclerView.Adapter<SearchDisplayRecycleAdapter.BookViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.search_display_item, parent, false)

        return BookViewHolder(view)
    }

    inner class BookViewHolder(mView: View) : RecyclerView.ViewHolder(mView), View.OnClickListener
    {
        val name : TextView = mView.findViewById(R.id.name)
        var BookImage : ImageView = mView.findViewById(R.id.BookImage1)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(book: Book) {
            name.text = book.title

            //adding an 's' to make it HTTPS since HTTP does not work
            book.thumbnail = book.thumbnail?.subSequence(0,4).toString() + "s" + book.thumbnail?.subSequence(4, book.thumbnail!!.length).toString()
            Glide.with(context)
                .load(book.thumbnail)
                .centerInside()
                .into(BookImage)
        }
        override fun onClick(v: View?) {
            // TODO: Get selected article
            val book = books[absoluteAdapterPosition]
            // TODO: Navigate to Details screen and pass selected article
            val intent = Intent(context, BookDetail::class.java)
            val bundle = Bundle()
            bundle.putSerializable("value", book)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return books.size
    }
}