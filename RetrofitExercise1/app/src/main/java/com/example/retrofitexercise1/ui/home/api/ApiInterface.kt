package com.example.retrofitexercise1.ui.home.api

import com.example.retrofitexercise1.ui.home.NowPlaying.Model.Details
import com.example.retrofitexercise1.ui.home.NowPlaying.Model.NowPlaying
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("now_playing")
    fun getNowPlaying(
            @Query("api_key") api_key :String,
            @Query("language") language : String,
            @Query("page") page :String
    ):Call<NowPlaying>

    @GET("{movie_id}")
    fun getDetails(
        @Path("movie_id") id:Int,
        @Query("api_key") api_key: String
    ):Call<Details>
}