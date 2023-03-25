package com.example.movie_list_mvvm.utils

import android.app.AlertDialog
import android.content.Context
import com.example.movie_list_mvvm.R

class Utility {

    companion object{

        fun isNullOrEmpty(str: String?): Boolean {
            return str == null || str.isEmpty()
        }
        fun showError(context: Context, message: String) {
            val builder = AlertDialog.Builder(context)
            val alertDialog: AlertDialog = builder
                .setTitle(R.string.caution)
                .setMessage(message)
                .setPositiveButton("Okay"){ dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .create()
            alertDialog.show()
        }



    }
}