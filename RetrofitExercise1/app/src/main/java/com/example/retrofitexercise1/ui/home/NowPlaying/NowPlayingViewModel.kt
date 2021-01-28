package com.example.retrofitexercise1.ui.home.NowPlaying

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitexercise1.ui.home.NowPlaying.Model.NowPlaying
import com.example.retrofitexercise1.ui.home.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingViewModel : ViewModel() {

    private var result : MutableLiveData<NowPlaying> = MutableLiveData()
    fun getResult(): LiveData<NowPlaying> = result

    fun loadNowPlaying(){
        var apiClient = ApiClient()
        var apiCall = apiClient.getNowPlaying(ApiClient.API_KEY,"en-US","1")

        apiCall.enqueue(object :Callback<NowPlaying>{
            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
                result.value = response.body()
            }

            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
                Log.d("Error>>>>>.",t.toString())
            }


        })
    }


}