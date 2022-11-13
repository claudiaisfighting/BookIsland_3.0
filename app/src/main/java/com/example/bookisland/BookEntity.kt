package com.example.bookisland

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.json.JSONArray

/**@Keep
@Serializable
 data class BookEntity(
 @SerialName("title")
  var title: String?,
 @SerialName("authors")
  var authors: String?,
 @SerialName("description")
  var description: String?,
 @SerialName("thumbnail")
  var thumbnail: String?,
 @SerialName("saleability")
  var saleability: String?,
 @SerialName("amount")
  var price: Double?, ) : java.io.Serializable
{}**/