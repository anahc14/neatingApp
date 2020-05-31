package com.higana.neating.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.annotation.Nullable
import com.higana.neating.databinding.RecipeInformationBinding
import com.higana.neating.databinding.RecipesFragmentBinding
import com.higana.neating.io.MyApiAdapter
import com.higana.neating.model.FullResponse
import com.higana.neating.model.SpoonRecipeInformation
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeInformationActivity : Activity() {

    private val myApiAdapter = MyApiAdapter()
    private lateinit var binding: RecipeInformationBinding
    private lateinit var recipeInformation: SpoonRecipeInformation

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipeInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

     //   displayRecipeView();
    }


//    fun getRecipeInformation(): SpoonRecipeInformation {
//        val call = myApiAdapter.getMyApiService(applicationContext)
//            .complexSearchRecipe(app_key = SPOON_APP_KEY, q = mainIngredient)
//        doAsync {
//
//            call.enqueue(object : Callback<FullResponse> {
//                override fun onFailure(call: Call<FullResponse>, t: Throwable) {
//                    Log.v("retrofit", "call failed")
//                }
//
//                override fun onResponse(
//                    call: Call<FullResponse>,
//                    response: Response<FullResponse?>
//                ) {
//                    myData.clear()
//                    if (response.isSuccessful) {
//                        myData.addAll(response.body()!!.results)
//                        displayRecyclerView()
//                    }
//
//                }
//
//
//            })
//        }
//        return recipeInformation
//    }

}