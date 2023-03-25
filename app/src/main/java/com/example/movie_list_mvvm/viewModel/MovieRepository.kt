package com.example.movie_list_mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.movie_list_mvvm.db.Movie
import com.example.movie_list_mvvm.db.MovieDao

class MovieRepository(private val movieDao: MovieDao) {


    val allMovies : LiveData<List<Movie>> = movieDao.getAllMovies()


    suspend fun insertMovie(movie: Movie){
        movieDao.insertMovie(movie)
    }

    suspend fun deleteMovie(movie: Movie){
        movieDao.deleteMovie(movie)
    }

    suspend fun updateMovie(movie: Movie){
       movieDao.updateMovie(movie)
    }

    fun searchByName(query: String): LiveData<List<Movie>> {
        return movieDao.searchByName(query)
    }
    val orderByDescending: LiveData<List<Movie>> = movieDao.orderByDescending()

}