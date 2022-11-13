package com.example.bookisland

import androidx.room.*

/**
@Entity(tableName = "profile")
data class ProfileEntity (
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "address") var emailAddress: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "collections") var  collections: List<CollectionEntity>?,
)**/





@Entity(tableName = "collection_table")
data class CollectionEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "bookList")  var bookList: MutableList<BookEntity>,
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
)

@kotlinx.serialization.Serializable
@Entity(tableName = "book_table")
data  class BookEntity(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "authors") var authors: String,
    @ColumnInfo(name = "description") var  description: String,
    @ColumnInfo(name = "thumbnail") var thumbnail: String,
    @ColumnInfo(name = "saleability") var saleability: String,
    //@ColumnInfo(name = "price") var price: Double
)