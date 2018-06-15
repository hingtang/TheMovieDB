package com.example.eastagile.themoviedb.data.souces

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.eastagile.themoviedb.data.Converters
import com.example.eastagile.themoviedb.data.Movie

@Database(version = 1, entities = [(Movie::class)])
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun moviesGatewayDao(): MoviesGatewayDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        MovieDatabase::class.java, "movie.db")
                        .build()
    }
}
