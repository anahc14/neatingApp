package com.higana.neating.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.higana.neating.R
import com.higana.neating.databinding.RecipeInformationBinding
import com.higana.neating.io.MyApiAdapter
import com.higana.neating.model.Ingredients
import com.higana.neating.model.RecipeFullInformation
import com.higana.neating.model.SpoonRecipeInformation
import com.higana.neating.ui.adapter.RecyclerIngredientsAdapter
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecipeInformationActivity : Activity() {

    private val myApiAdapter = MyApiAdapter()
    private lateinit var binding: RecipeInformationBinding
    private lateinit var recipeBasicInformation: SpoonRecipeInformation
    private var recipeFullInformation: RecipeFullInformation = RecipeFullInformation()
    private lateinit var adapter: RecyclerIngredientsAdapter

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding = RecipeInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recipeBasicInformation = intent.getParcelableExtra("RECIPE")
        getRecipeInformation()

    }

    fun displayRecyclerView(myData: ArrayList<Ingredients>) {
        adapter = RecyclerIngredientsAdapter(myData)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.isNestedScrollingEnabled = false
        binding.recyclerView.visibility = View.VISIBLE
    }


    fun getRecipeInformation(): RecipeFullInformation {
        val call = myApiAdapter.getMyApiService(applicationContext)
            .getRecipeInformation(
                app_key = getString(R.string.app_key),
                id = recipeBasicInformation.id.toString()
            )
        doAsync {

            call.enqueue(object : Callback<RecipeFullInformation> {
                override fun onFailure(call: Call<RecipeFullInformation>, t: Throwable) {
                    Log.d("RECIPEINFORMATION: ", "failed")
                }

                override fun onResponse(
                    call: Call<RecipeFullInformation>,
                    response: Response<RecipeFullInformation?>
                ) {
                    if (response.isSuccessful) {
                        recipeFullInformation = response.body()!!
                        Picasso.get().load(recipeFullInformation.image).resize(800, 450)
                            .centerCrop().into(binding.recipeImage)
                        binding.recipeTitle.setText(recipeFullInformation.title)
                        binding.recipeSummary.setText(
                            Html.fromHtml(recipeFullInformation.summary)
                        )
                        var myIngredients: ArrayList<Ingredients> = ArrayList()
                        myIngredients.addAll(recipeFullInformation.extendedIngredients)
                        displayRecyclerView(myIngredients)

                    }

                }


            })
        }
        return recipeFullInformation
    }

    class CustomLinearLayoutManager(
        context: Context?,
        orientation: Int,
        reverseLayout: Boolean
    ) :
        LinearLayoutManager(context, orientation, reverseLayout) {

        override fun canScrollVertically(): Boolean {
            return false
        }
    }

}