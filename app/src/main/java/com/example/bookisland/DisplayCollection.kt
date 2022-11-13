package com.example.bookisland

data class DisplayCollection(
    var name: String?,
    var collection: MutableList<BookEntity>
) :java.io.Serializable