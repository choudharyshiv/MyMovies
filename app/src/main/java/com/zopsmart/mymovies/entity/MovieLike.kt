package com.zopsmart.mymovies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="MovieLike")
data class MovieLike(

    @PrimaryKey
    val movieId : Int,
    val isLike  : Boolean
)