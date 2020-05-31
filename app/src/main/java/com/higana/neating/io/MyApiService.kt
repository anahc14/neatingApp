package com.higana.neating.io


import com.higana.neating.model.FullResponse
import com.higana.neating.model.RecipeFullInformation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApiService {

    @GET("search")
    fun getSpoonData(
        @Query("apiKey") app_key: String,
        @Query("query") q: String
    ): Call<FullResponse>

    @GET("complexSearch")
    fun complexSearchRecipe(
        @Query("apiKey") app_key: String,
        @Query("query") q: String
    ): Call<FullResponse>


    @GET("{id}/information")
    fun getRecipeInformation(
        @Path ("id") id: String,
        @Query("apiKey") app_key: String
    ): Call<RecipeFullInformation>
}