package com.example.movie_list_mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movie_list_mvvm.tableName

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie (movie: Movie)

    @Delete
    suspend fun deleteMovie (movie: Movie)

    @Query("Select * from $tableName order by id ASC")
    fun getAllMovies(): LiveData<List<Movie>>

    @Update
    suspend fun updateMovie (movie: Movie)

    @Query("SELECT * FROM $tableName WHERE movieName LIKE :query")
    fun searchByName(query: String): LiveData<List<Movie>>

    @Query("SELECT * FROM $tableName ORDER BY date DESC")
    fun orderByDescending(): LiveData<List<Movie>>

}