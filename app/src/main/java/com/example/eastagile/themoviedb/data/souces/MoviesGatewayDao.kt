package com.example.eastagile.themoviedb.data.souces

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.eastagile.themoviedb.data.Movie

@Dao
interface MoviesGatewayDao{
    @Insert
    fun insertFavouriteMovie(movie: Movie)

    @Query("SELECT * FROM movie where id = :id")
    fun isMovieExistWithMovieId(id: Int): Boolean

    @Query("SELECT * FROM movie")
    fun getFavouriteMovie(): List<Movie>

    @Query("DELETE FROM movie")
    fun clearDatabase()

    @Query("DELETE FROM movie where id = :id")
    fun removeFavouriteMovie(id: Int): Int
}
