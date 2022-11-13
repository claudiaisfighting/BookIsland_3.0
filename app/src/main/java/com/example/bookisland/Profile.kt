package com.example.bookisland

data class Profile(
    var emailAddress : String,
    var password: String,
    var collections: MutableList<BookCollection>,
) : java.io.Serializable
{}