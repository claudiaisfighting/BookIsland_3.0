package com.example.bookisland

import androidx.annotation.Keep
import androidx.annotation.Size
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class BookCollection(
    var name: String?,
    var bookList: MutableList<Book>?,
) : java.io.Serializable
{}