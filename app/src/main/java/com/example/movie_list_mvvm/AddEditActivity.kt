package com.example.movie_list_mvvm

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64.encodeToString
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movie_list_mvvm.databinding.ActivityAddEditBinding
import com.example.movie_list_mvvm.db.Movie
import com.example.movie_list_mvvm.utils.Utility.Companion.isNullOrEmpty
import com.example.movie_list_mvvm.utils.Utility.Companion.showError
import com.example.movie_list_mvvm.utils.dataBinding
import com.example.movie_list_mvvm.viewModel.ViewModelMovie
import java.io.ByteArrayOutputStream
import java.util.*
import android.util.Base64


class AddEditActivity : AppCompatActivity() {

    // binding
    private val binding: ActivityAddEditBinding by dataBinding(R.layout.activity_add_edit)

    lateinit var currentType: Type
    private lateinit var currentMovie: Movie

    private lateinit var viewModel: ViewModelMovie

    private lateinit var nameText: String
    private lateinit var descText: String
    private lateinit var dateText: String
    private lateinit var dirText: String

    private val pickImage = 100
    private var imageUri: Uri? = null

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        currentType = intent.getSerializableExtra(EXT_1) as Type

        init()
        listeners()




    }

    private fun init(){

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ViewModelMovie::class.java]

        when (currentType) {
            Type.CREATE -> {
                initForCreate()
            }
            Type.EDIT -> {
                initForEdit()
            }
        }


    }


    private fun initForCreate(){
        binding.btnAdd.text = resources.getString(R.string.add_note)
    }

    private fun initForEdit(){
        binding.btnAdd.text = resources.getString(R.string.edit_note)
        binding.txtHeader.text = resources.getString(R.string.edit_note)
        currentMovie = intent.getSerializableExtra(EXT_2) as Movie

        binding.edtName.setText(currentMovie.movieName)
        binding.edtDate.setText(currentMovie.date)
        binding.edtDesc.setText(currentMovie.description)
        binding.edtDirector.setText(currentMovie.directorName)
    }

    private fun listeners(){


        binding.btnAdd.setOnClickListener {
            when (currentType) {
                Type.CREATE -> {
                    processForCreate()
                }
                Type.EDIT -> {
                    processForEdit()
                }
            }
        }


    }

    private fun processForCreate(){
        if(!checkEditTextFields()){
            val newMovie = Movie(
                nameText,
                dateText,
                dirText,
                descText

            )
            viewModel.addMovie(newMovie)
            finalize(newMovie)
        }else{
            showError(this, resources.getString(R.string.field_required_alert))
        }

    }

    private fun processForEdit(){
        if(!checkEditTextFields()){
            val newMovie = Movie(
                nameText,
                dateText,
                dirText,
                descText,

            )
            newMovie.id = currentMovie.id
            viewModel.updateMovie(newMovie)
            finalize(newMovie)
        } else {
            showError(this , resources.getString(R.string.field_required_alert))
        }
    }

    private fun checkEditTextFields(): Boolean{
        nameText = binding.edtName.text.toString()
        descText = binding.edtDesc.text.toString()
        dateText = binding.edtDate.text.toString()
        dirText = binding.edtDirector.text.toString()

        return isNullOrEmpty(nameText) || isNullOrEmpty(descText)
    }

    private fun finalize(movie: Movie){
        intent.putExtra(EXT_1, movie)
        setResult(RESULT_OK, intent)
        finish()
    }



    companion object {
        @JvmStatic
        fun create(context: Context, type: Type) =
            Intent(context, AddEditActivity::class.java)
                .putExtra(EXT_1, type)

        @JvmStatic
        fun create(context: Context, type: Type, movie: Movie) =
            Intent(context, AddEditActivity::class.java)
                .putExtra(EXT_1, type)
                .putExtra(EXT_2, movie)
    }

    enum class Type {
        CREATE, EDIT
    }
}