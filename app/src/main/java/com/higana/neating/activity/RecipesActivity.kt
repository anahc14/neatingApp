package com.higana.neating.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import com.higana.neating.databinding.RecipesFragmentBinding
import com.higana.neating.io.MyApiAdapter
import com.higana.neating.model.ResponseModel
import com.higana.neating.ui.adapter.RecyclerAdapter
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipesActivity : Activity() {

    // https://api.edamam.com/search?q=chicken&app_id=6b2f6ab3&app_key=21a6e4dac13276e2598ceb45647f574c
    // https://api.edamam.com/search?q=avocado&app_id=6b2f6ab3&app_key=21a6e4dac13276e2598ceb45647f574c

    private val APP_KEY: String = "21a6e4dac13276e2598ceb45647f574c"
    private val APP_ID: String = "6b2f6ab3"
    private lateinit var binding: RecipesFragmentBinding
    private lateinit var mainIngredient: String
    private lateinit var recipes: ArrayList<ResponseModel.RecipeInformation>
    private val myApiAdapter = MyApiAdapter()
    private var myData: ArrayList<ResponseModel.RecipeInformation> =
        ArrayList<ResponseModel.RecipeInformation>()
    private val recyclerAdapter: RecyclerAdapter = RecyclerAdapter(myData)


    fun getMyData(): ArrayList<ResponseModel.RecipeInformation> {
        return myData
    }

    private val myDataSet: ArrayList<ResponseModel.RecipeInformation> = (
            ArrayList())

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipesFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            mainIngredient = binding.editText.text.toString()
            recipes = getRecipes()
        }

    }

    fun getRecipes(): ArrayList<ResponseModel.RecipeInformation> {
        val call = myApiAdapter.getMyApiService(applicationContext)
            .getData(app_id = APP_ID, app_key = APP_KEY, q = mainIngredient)
        doAsync {
            call.enqueue(object : Callback<ResponseModel> {
                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Log.v("retrofit", "call failed")
                }

                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    myData.addAll(response.body()!!.hits)
                    if (response.isSuccessful) {
                        val intent = Intent(applicationContext, RecipesResultActivity::class.java)
                        intent.putExtra("recipes", myData)
                        startActivity(intent)
                    }
                }

            })

        }
        return myData
    }

}
