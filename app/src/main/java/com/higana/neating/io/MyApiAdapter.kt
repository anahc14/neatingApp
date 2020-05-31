package com.higana.neating.io

import android.content.Context
import android.provider.Settings.Global.getString
import com.higana.neating.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApiAdapter {

    private lateinit var context: Context
    private lateinit var retrofitInstance: Retrofit

    fun getMyApiService(context: Context) : MyApiService{
        this.context = context
        retrofitInstance = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitInstance.create(MyApiService::class.java)
    }



}