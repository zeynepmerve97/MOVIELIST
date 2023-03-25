package com.example.movie_list_mvvm.db

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room


@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDataBase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object{

        @Volatile
        private var instance : MovieDataBase? = null

        fun getDatabaseInstance ( context: Context): MovieDataBase{
            return instance ?: synchronized(this){
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDataBase::class.java,
                    "movie_database"
                ).build()
                instance = newInstance
                newInstance
            }}
    }
}