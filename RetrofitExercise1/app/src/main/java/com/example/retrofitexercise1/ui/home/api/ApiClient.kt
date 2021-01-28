package com.example.retrofitexercise1.ui.home.api

import com.example.retrofitexercise1.ui.home.NowPlaying.Model.Details
import com.example.retrofitexercise1.ui.home.NowPlaying.Model.NowPlaying
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val Base_Url ="https://api.themoviedb.org/3/movie/"

    companion object{
        val API_KEY = "af0989e25db58f1170b9f1f00ed6f877"
        val Image_Patch = "https://image.tmdb.org/t/p/w500/"
    }

    val apiInterface : ApiInterface

    init {
        val retrofit = Retrofit.Builder().baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create()).build()
        apiInterface = retrofit.create(ApiInterface::class.java)

    }
    fun getNowPlaying(
            api_key : String,
            language: String,
            page : String
    ): Call<NowPlaying> {
        return apiInterface.getNowPlaying(api_key,language,page)
    }
    fun getDetails(
        id : Int,
        api_key : String
    ): Call<Details>{
        return  apiInterface.getDetails(id,api_key)
    }
}