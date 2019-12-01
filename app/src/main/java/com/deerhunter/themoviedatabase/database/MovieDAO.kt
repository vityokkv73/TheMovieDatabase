package com.deerhunter.themoviedatabase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deerhunter.themoviedatabase.data.Movie

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movie: Movie)

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieDetailsById(movieId: Int): LiveData<Movie>
}