package com.charmist.moviesearchdemo.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie_list")
@Parcelize
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String = "",
    @ColumnInfo(name = "original_language")
    val original_language: String,
    @ColumnInfo(name = "original_title")
    val original_title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int
) : Parcelable {
    constructor() :
            this(
                0,
                false,
                "",
                "",
                "",
                "",
                0.0,
                "",
                "",
                "",
                false,
                0.0,
                0
            )
}