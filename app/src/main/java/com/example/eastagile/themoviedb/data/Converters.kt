package com.example.eastagile.themoviedb.data

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson

class Converters{

    @TypeConverter
    fun fromGenreIdsString(value: String): ArrayList<Int>{
        return Gson().fromJson<ArrayList<Int>>(value)
    }

    @TypeConverter
    fun fromGenereIdList(value: List<Int>): String{
        return Gson().toJson(value)
    }

    inline fun <reified T> Gson.fromJson(value:String) =  this.fromJson<T>(value, object: com.google.gson.reflect.TypeToken<T>() {}.type)

}
