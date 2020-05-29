package com.higana.neating.io

import com.higana.neating.model.ResponseModel
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface MyApiService {

    @GET("search")
    fun getData(
        @Query("q") q: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String
    ): Call<ResponseModel>

}