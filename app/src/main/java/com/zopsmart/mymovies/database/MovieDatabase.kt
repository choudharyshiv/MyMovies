package com.zopsmart.mymovies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zopsmart.mymovies.dao.MovieLikeDao
import com.zopsmart.mymovies.entity.MovieLike

@Database(entities = [MovieLike :: class],version = 3)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieLikeDao() : MovieLikeDao

}