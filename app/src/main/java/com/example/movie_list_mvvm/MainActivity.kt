package com.example.movie_list_mvvm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.query
import com.example.movie_list_mvvm.databinding.ActivityMainBinding


import com.example.movie_list_mvvm.db.Movie
import com.example.movie_list_mvvm.utils.RowClickListener
import com.example.movie_list_mvvm.utils.dataBinding
import com.example.movie_list_mvvm.viewModel.ViewModelMovie
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity()  {


    private val viewModel: ViewModelMovie by viewModels()
    private val binding: ActivityMainBinding by dataBinding(R.layout.activity_main)
    var movieAdapter : MovieAdapter? = null
    var isDescendingOrder = false
    private var createActivityLauncher : ActivityResultLauncher<Intent>? = null
    private var editActivityLauncher : ActivityResultLauncher<Intent>? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()
        listeners()


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init(){

        createActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            activityResult(REQ_CREATE_MOVIE, result)
        }

        editActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            activityResult(REQ_EDIT_MOVIE, result)
        }


        viewModel.allMovies.observe(this) { movieList ->
            movieList?.let {
                movieAdapter = MovieAdapter(movieList, movieClickListener, viewModel, editActivityLauncher, this@MainActivity)
                binding.rvMovie.adapter = movieAdapter

                binding.rvMovie.layoutManager = LinearLayoutManager(this)

            } ?: kotlin.run {
                Log.d("xxx", "EmptyMovieList")
            }

        }

    }

    fun listeners(){


        // ORDER THE DATA
        binding.btnOrder.setOnClickListener {
            if (isDescendingOrder) {
                // return the old version
                viewModel.orderByOldVersion.observe(this) { movieList ->
                    movieList.let {
                        movieAdapter = MovieAdapter(
                            movieList,
                            movieClickListener,
                            viewModel,
                            editActivityLauncher,
                            this
                        )
                        binding.rvMovie.adapter = movieAdapter
                        isDescendingOrder = !isDescendingOrder
                    }
                }
            }else {
                // Sort the data in descending order
                viewModel.orderByDescending.observe(this) {
                        movieList -> movieList.let {
                    movieAdapter = MovieAdapter(movieList,movieClickListener,viewModel,editActivityLauncher,this)
                    binding.rvMovie.adapter = movieAdapter
                    isDescendingOrder = !isDescendingOrder
                }
                }
            }

        }


        // SEARCH MOVIE BY NAME

        binding.edtSearch.doAfterTextChanged { text ->
            searchMovie(text.toString())
        }



        // ADD NEW MOVIE

        binding.btnAdd.setOnClickListener {
            createActivityLauncher?.launch(AddEditActivity.create(this@MainActivity, AddEditActivity.Type.CREATE))
        }




    }



    private fun activityResult(reqCreateMovie: Int, result: ActivityResult) {

        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val item = intent?.getSerializableExtra(EXT_1) as Movie
            when (reqCreateMovie) {
                REQ_EDIT_MOVIE -> {
                    Toast.makeText(this, "You updated the note as ${item.movieName}", Toast.LENGTH_SHORT).show()
                }
                REQ_CREATE_MOVIE -> {
                    Toast.makeText(this, "You successfully created the ${item.movieName} note", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }



    private val movieClickListener = object : RowClickListener<Serializable> {
        override fun onRowClick(row: Int, item: Serializable) {
            val m = item as Movie
            val i = Intent(this@MainActivity, DescriptionActivity::class.java)
            i.putExtra(EXT_1, m)
            startActivity(i)
            //editActivityLauncher?.launch(AddEditActivity.create(this@MainActivity, AddEditActivity.Type.EDIT,m))

        }
    }




    private fun searchMovie(query: String) {



        viewModel.searchByName("%$query%").observe(this) { movieList ->
            movieAdapter?.updateMovies(movieList)
        }
        }
    }











