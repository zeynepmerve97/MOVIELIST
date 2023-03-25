package com.example.movie_list_mvvm

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_list_mvvm.databinding.ListMovieItemBinding
import com.example.movie_list_mvvm.db.Movie
import com.example.movie_list_mvvm.utils.RowClickListener
import com.example.movie_list_mvvm.viewModel.ViewModelMovie
import java.io.Serializable

class MovieAdapter(
    var movieList: List<Movie>,
    val onClickListener: RowClickListener<Serializable>,
    val viewModelMovie: ViewModelMovie,
    val editActivityLauncher: ActivityResultLauncher<Intent>?,
    val mainActivity: MainActivity
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListMovieItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(newMovieList: List<Movie>) {
        movieList = newMovieList
        notifyDataSetChanged()
    }


    override fun getItemCount() : Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bind(movieList[position], onClickListener)
        val movie = movieList[position]
        holder.binding.txtMovie.text = movie.movieName
        holder.binding.txtDate.text = movie.date
        holder.binding.txtDirector.text = movie.directorName
        holder.binding.root.setOnClickListener {
            onClickListener.onRowClick(position, movie)
        }
        holder.binding.rvDelete.setOnClickListener {
            viewModelMovie.deleteMovie(movie)
        }

        holder.binding.rvEdit.setOnClickListener {
            editActivityLauncher?.launch(AddEditActivity.create(mainActivity, AddEditActivity.Type.EDIT,movieList[position]))

        }




    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListMovieItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_movie_item,parent , false)
        return ViewHolder(binding)
    }




}