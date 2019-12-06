package com.deerhunter.themoviedatabase.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deerhunter.themoviedatabase.data.Movie

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movie: Movie)

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovieDetailsById(movieId: Int): Movie?
}