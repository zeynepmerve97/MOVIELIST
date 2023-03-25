package com.example.movie_list_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie_list_mvvm.databinding.ActivityDescriptionBinding
import com.example.movie_list_mvvm.databinding.ActivityMainBinding
import com.example.movie_list_mvvm.db.Movie
import com.example.movie_list_mvvm.utils.dataBinding
import com.example.movie_list_mvvm.viewModel.ViewModelMovie

class DescriptionActivity : AppCompatActivity() {



    private var viewModel: ViewModelMovie? = null
    private val binding: ActivityDescriptionBinding by dataBinding(R.layout.activity_description)
    var description: Movie? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        description = intent.extras?.getSerializable(EXT_1) as Movie

        binding.txtDesc.text = description!!.description



        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ViewModelMovie::class.java]


    }
}