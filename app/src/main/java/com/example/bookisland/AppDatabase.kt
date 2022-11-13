package com.example.bookisland

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.ToNumberPolicy
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.lang.reflect.Type

@Database(entities = [CollectionEntity::class], version = 2)
@TypeConverters(BookConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun CollectionsDao(): CollectionsDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Collections-db"
            ).fallbackToDestructiveMigration()
                .build()
    }
}

abstract class Converters<T> {

    @TypeConverter
    fun mapListToString(value: MutableList<T>): String {
        val gson = Gson()
        val type = object : TypeToken<MutableList<T>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): MutableList<T> {
        val gson = Gson()
        val type = object : TypeToken<MutableList<T>>() {}.type
        return gson.fromJson(value, type)
    }
}
class BookConverter : Converters<BookEntity>()

/**fun getDataInArrayList(key: String): ArrayList<ProductData> {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    val json = prefs.getString(key, null)
    val type = object : TypeToken<ArrayList<ProductData>>() {
    }.type
    return Gson().fromJson(json,type)
}

fun getDataInArrayList(key: String): ArrayList<ProductData> {

    val emptyList = Gson().toJson(ArrayList<ProductData>())
    return Gson().fromJson(
        sharedPreferences.getString(key, emptyList),
        object : TypeToken<ArrayList<ProductData>>() {
        }.type
    )
}
class BookConverter {
    @TypeConverter
    fun bookToString(book: BookEntity): String {
        return JSONObject().apply {
            put("title", book.title)
            put("authors", book.authors)
            put("description", book.description)
            put("thumbnail", book.thumbnail)
            put("saleability", book.saleability)
            put("price", book.price)
        }.toString()
    }

    @TypeConverter
    fun stringToBook(value: String): BookEntity {
        val json = JSONObject(value)
        return BookEntity(json.getString("title"),
            json.getString("authors"),json.getString("description"),
            json.getString("thumbnail"), json.getString("saleability"),
            json.getDouble("price"))
    }
}


abstract class BaseConverter<T>(private val type: Type) {
    private val gson: Gson = GsonBuilder()
        .serializeNulls()
        .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
        .create()

    @TypeConverter
    fun mapStringToList(value: String): MutableList<T> {
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun mapListToString(value: MutableList<T>): String {
        return gson.toJson(value, type)
    }
}

class BookConverter :
    BaseConverter<BookEntity?>(object : TypeToken<MutableList<BookEntity?>?>() {}.type)


abstract class Converters<BookCollection> {

    @TypeConverter
    fun mapListToString(value: List<BookCollection>): String {
        val gson = Gson()
        val type = object : TypeToken<List<BookCollection>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): List<BookCollection> {
        val gson = Gson()
        val type = object : TypeToken<List<BookCollection>>() {}.type
        return gson.fromJson(value, type)
    }

    abstract class Converters<Book> {

        @TypeConverter
        fun mapListToString(value: List<Book>): String {
            val gson = Gson()
            val type = object : TypeToken<List<Book>>() {}.type
            return gson.toJson(value, type)
        }

        @TypeConverter
        fun mapStringToList(value: String): List<Book> {
            val gson = Gson()
            val type = object : TypeToken<List<Book>>() {}.type
            return gson.fromJson(value, type)
        }
    }
}**/

