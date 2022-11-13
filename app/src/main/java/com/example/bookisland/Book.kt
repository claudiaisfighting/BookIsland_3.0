package com.example.bookisland

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
 data class Book(
 @SerialName("title")
 var title: String?,
 @SerialName("authors")
 var authors: String?,
 @SerialName("description")
 var description: String?,
 @SerialName("thumbnail")
 var thumbnail: String?,
 @SerialName("saleability")
 var saleability: String?, ) : java.io.Serializable{}
 //@SerialName("amount")
 //var price: Double?,
