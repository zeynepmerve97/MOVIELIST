package com.example.movie_list_mvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.movie_list_mvvm.db.Movie
import com.example.movie_list_mvvm.db.MovieDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelMovie(application: Application): AndroidViewModel(application) {

    val allMovies: LiveData<List<Movie>>

    private val repository: MovieRepository

    val orderByDescending : LiveData<List<Movie>>
    val orderByOldVersion : LiveData<List<Movie>>

    init {
        val dao = MovieDataBase.getDatabaseInstance(application).getMovieDao()
        repository = MovieRepository(dao)
        allMovies = repository.allMovies
        orderByDescending = repository.orderByDescending
        orderByOldVersion = allMovies
    }

    fun addMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertMovie(movie)
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteMovie(movie)
    }

    fun updateMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateMovie(movie)
    }

   /* fun orderMovie(listMovie: List<Movie>): List<Movie>{
        val sortedList = ArrayList(listMovie.sortedByDescending { it.date.toInt() })

        sortedList.forEach { println(it.date) }
        return sortedList
    }*/

    fun searchByName(str: String): LiveData<List<Movie>>  {
        return repository.searchByName(str)
    }



}


