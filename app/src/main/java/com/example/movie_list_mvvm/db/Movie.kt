package com.example.movie_list_mvvm.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movie_list_mvvm.tableName
import java.io.Serializable
import java.util.Date

@Entity (tableName = tableName)
class Movie(
    @ColumnInfo(name = "movieName")
    val movieName: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "directorName")
    val directorName: String,
    @ColumnInfo(name = "desc")
    val description: String,


): Serializable {
    @PrimaryKey(autoGenerate = true) var id = 0
}