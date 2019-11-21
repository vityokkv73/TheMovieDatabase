package com.deerhunter.themoviedatabase.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deerhunter.themoviedatabase.data.PopularMovieBrief

@Dao
interface PopularMovieBriefDAO {
    @Query("SELECT * FROM popular_movie_briefs")
    fun loadAll(): DataSource.Factory<Int, PopularMovieBrief>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg popularMovieBriefs: PopularMovieBrief)
}