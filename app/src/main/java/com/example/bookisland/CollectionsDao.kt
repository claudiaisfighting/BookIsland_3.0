package com.example.bookisland


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CollectionsDao {

    @Query("SELECT * FROM collection_table")
    fun getCollections() : Flow<List<CollectionEntity>>

    @Query("SELECT * FROM collection_table WHERE name= :name")
    fun getCollection(name: String) : CollectionEntity

    @Insert
    fun insert(collection : CollectionEntity)

    @Query("DELETE FROM collection_table WHERE name= :name" )
    fun delete(name: String)

    //@Query("DELETE FROM collection_table WHERE bookList")
    //fun delete()

    //@Query("DELETE FROM collection_table WHERE name= :name" )
    @Delete
    fun deleteCollection(collection: CollectionEntity)

    @Query("DELETE FROM collection_table")
    fun deleteAll()
}