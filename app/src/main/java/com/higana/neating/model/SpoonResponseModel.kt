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