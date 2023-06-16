package com.amar.hafidzpro.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amar.hafidzpro.model.nearby.ModelResults
import com.amar.hafidzpro.model.response.ModelResultNearby
import com.amar.hafidzpro.networking.ApiInterface
import com.amar.hafidzpro.networking.ApiMaps
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MasjidViewModel : ViewModel() {

    private val modelResultsMutableLiveData = MutableLiveData<ArrayList<ModelResults>>()
    var strApiKey = "YOUR API KEY"

    fun setMarkerLocation(strLocation: String) {
        val apiService: ApiInterface = ApiMaps.getMaps()

        val call = apiService.getDataResult(strApiKey, "Masjid", strLocation, "distance")
        call.enqueue(object : Callback<ModelResultNearby> {
            override fun onResponse(call: Call<ModelResultNearby>, response: Response<ModelResultNearby>) {
                if (!response.isSuccessful) {
                    Log.e("response", response.toString())
                } else if (response.body() != null) {
                    val items = ArrayList(response.body()?.modelResults)
                    modelResultsMutableLiveData.postValue(items)
                }
            }

            override fun onFailure(call: Call<ModelResultNearby>, t: Throwable) {
                Log.e("failure", t.toString())
            }
        })
    }

    fun getMarkerLocation(): LiveData<ArrayList<ModelResults>> = modelResultsMutableLiveData

}