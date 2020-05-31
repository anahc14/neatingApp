package com.higana.neating.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FullResponse(@SerializedName("results") var results: List<SpoonRecipeInformation>) :
    Parcelable


@Parcelize
data class SpoonResponseModel(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("readyInMinutes") var readyInMinutes: String,
    @SerializedName("servings") var servings: String,
    @SerializedName("image") var image: String
) : Parcelable


@Parcelize
data class SpoonRecipeInformation(
    @SerializedName("id") var id: Int,
    @SerializedName("calories") var calories: Int,
    @SerializedName("image") var image: String,
    @SerializedName("title") var title: String
) : Parcelable

@Parcelize
data class RecipeFullInformation(
    @SerializedName("healthScore") var healthScore: Int = 0,
    @SerializedName("extendedIngredients") var extendedIngredients: List<Ingredients> = emptyList(),
    @SerializedName("title") var title: String = "",
    @SerializedName("readyInMinutes") var readyInMinutes: Int = 0,
    @SerializedName("servings") var servings: Int = 0,
    @SerializedName("image") var image: String = "",
    @SerializedName("summary") var summary: String = ""
) : Parcelable

@Parcelize
data class Ingredients(
    @SerializedName("name") var name: String,
    @SerializedName("measures") var measures: Metric
) : Parcelable

@Parcelize
data class Metric(
    @SerializedName("metric") var metric: AmountInMetric
) : Parcelable

@Parcelize
data class AmountInMetric(
    @SerializedName("amount") var amount: Double,
    @SerializedName("unitShort") var unitShort: String
) : Parcelable