package com.example.retrofitexercise1.ui.home.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitexercise1.ui.home.NowPlaying.Model.Details
import com.example.retrofitexercise1.ui.home.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private var result : MutableLiveData<Details> = MutableLiveData()
    fun getResult() : LiveData<Details> = result

    fun loadDetails(id:Int)
    {
        var apiClient = ApiClient()
        var apiCall = apiClient.getDetails(id,ApiClient.API_KEY)

        apiCall.enqueue(object : Callback<Details>{
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                result.value=response.body()
            }

            override fun onFailure(call: Call<Details>, t: Throwable) {
                Log.d("Error>>>>>>",t.toString())
            }

        })
    }
}