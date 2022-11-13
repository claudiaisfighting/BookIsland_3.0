package com.example.bookisland

import android.app.Application

class CollectionApplication : Application(){
    val db by lazy { AppDatabase.getInstance(this) }
}
