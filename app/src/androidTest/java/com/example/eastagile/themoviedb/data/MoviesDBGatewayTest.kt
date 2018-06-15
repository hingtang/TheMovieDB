package com.example.eastagile.themoviedb.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.eastagile.themoviedb.data.souces.MovieDatabase
import com.example.eastagile.themoviedb.data.souces.MoviesGatewayDao
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesDBGatewayTest {
    private var moviesGatewayDao: MoviesGatewayDao? = null
    private var movieDatabase: MovieDatabase? = null

    @Before
    fun setUp() {
        movieDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getTargetContext(),
                MovieDatabase::class.java
        ).build()
        moviesGatewayDao = movieDatabase!!.moviesGatewayDao()
    }

    @Test
    fun should_return_movie_list_when_user_get_favourite_movie_list() {
        moviesGatewayDao!!.clearDatabase()
        moviesGatewayDao!!.insertFavouriteMovie(Movie(MOVIE_ID, title = MOVIE_TITLE))

        val movieList = moviesGatewayDao!!.getFavouriteMovie()
        assertTrue(movieList.size > 0)
    }

    @Test
    fun should_insert_movie_when_user_add_favourite_movie() {
        moviesGatewayDao!!.clearDatabase()
        moviesGatewayDao!!.insertFavouriteMovie(Movie(MOVIE_ID, title = MOVIE_TITLE))

        val isFavoured = moviesGatewayDao!!.isMovieExistWithMovieId(MOVIE_ID)
        assertTrue(isFavoured)
    }

    @Test
    fun should_return_true_when_user_favored_a_movie() {
        moviesGatewayDao!!.clearDatabase()
        moviesGatewayDao!!.insertFavouriteMovie(Movie(MOVIE_ID, title = MOVIE_TITLE))

        val isFavoured = moviesGatewayDao!!.isMovieExistWithMovieId(MOVIE_ID)
        assertTrue(isFavoured)
    }

    companion object {
        private const val MOVIE_ID = 1
        private const val MOVIE_TITLE = "Title"
    }
}
