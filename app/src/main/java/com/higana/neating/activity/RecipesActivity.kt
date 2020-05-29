package com.higana.neating.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import com.higana.neating.databinding.RecipesFragmentBinding
import com.higana.neating.io.MyApiAdapter
import com.higana.neating.model.ResponseModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Response

class RecipesActivity : Activity() {

    // https://api.edamam.com/search?q=chicken&app_id=6b2f6ab3&app_key=21a6e4dac13276e2598ceb45647f574c
    // https://api.edamam.com/search?q=avocado&app_id=6b2f6ab3&app_key=21a6e4dac13276e2598ceb45647f574c

    private val APP_KEY: String = "21a6e4dac13276e2598ceb45647f574c"
    private val APP_ID: String = "6b2f6ab3"
    private lateinit var binding: RecipesFragmentBinding
    private lateinit var mainIngredient: String

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipesFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            mainIngredient = binding.editText.text.toString()
            doAsync {
                val myApiAdapter = MyApiAdapter()
                val call = myApiAdapter.getMyApiService(applicationContext)
                    .getData(app_id = APP_ID, app_key = APP_KEY, q = mainIngredient).execute()
                val recipes = call.body() as ResponseModel
               binding.result.setText("Recipe" + recipes.hits[0].recipe.ingredients)
            }


        }

    }


}