package com.example.movie_list_mvvm.utils

interface RowClickListener<T> {
    fun onRowClick(row: Int, item:T)
}