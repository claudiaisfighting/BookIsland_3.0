package com.example.bookisland

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.text.method.ScrollingMovementMethod
import android.widget.Button

class BookDetail: AppCompatActivity() {
    private lateinit var BookTitle: TextView
    private lateinit var BookAuthors: TextView
    private lateinit var BookImage: ImageView
    private lateinit var BookDescription: TextView
    private lateinit var BookSaleability: TextView
    private lateinit var AddOrRemove: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_detail)
        BookAuthors= findViewById(R.id.authors)
        BookTitle = findViewById(R.id.BookTitle)
        BookImage = findViewById(R.id.BookImage)
        BookDescription = findViewById(R.id.Description)
        BookSaleability = findViewById(R.id.saleability)
        AddOrRemove = findViewById(R.id.AddOrRemove)

        val btnTolist = findViewById<Button>(R.id.notes)
        btnTolist.setOnClickListener{
            val Intent = Intent(this, MainActivity3::class.java)
            startActivity(Intent)
        }



        val bundle = intent.extras
        val book = bundle?.getSerializable("value") as Book

        //BookPrice.text = "Price: " + book.price.toString() + "USD"
        BookTitle.text = "Title: " + book.title
        BookDescription.text = book.description
        BookSaleability.text = "Availability: " + book.saleability
        BookAuthors.text = "Authors: " + book.authors
        BookDescription.movementMethod = ScrollingMovementMethod()
        Glide.with(this)
            .load(book.thumbnail)
            .centerInside()
            .into(BookImage)

        AddOrRemove.setOnClickListener{
            val i = Intent(this,AddOrRemoveBook::class.java)
            i.putExtra("book",book)
            startActivity(i)
        }
    }

}

