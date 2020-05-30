package com.higana.neating.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class ResponseModel {

    @SerializedName("q")
    lateinit var q: String

    @SerializedName("hits")
    lateinit var hits: List<RecipeInformation>

    @Parcelize
    data class RecipeInformation(@SerializedName("recipe") var recipe: Recipe) : Parcelable

    @Parcelize
    data class Recipe(
        @SerializedName("label") var label: String,
        @SerializedName("yield") var yield: String,
        @SerializedName("dietLabels") var dietLabels: List<String>,
        @SerializedName("healthLabels") var healthLabels: List<String>,
        @SerializedName("cautions") var cautions: List<String>,
        @SerializedName("ingredients") var ingredients: List<Ingredients>
    ) : Parcelable

    @Parcelize
    data class Ingredients(
        @SerializedName("text") var text: String,
        @SerializedName("weight") var weight: Double
    ) : Parcelable

}