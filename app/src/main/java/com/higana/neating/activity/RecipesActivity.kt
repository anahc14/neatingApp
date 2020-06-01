package com.higana.neating.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.higana.neating.R
import com.higana.neating.databinding.RecipesFragmentBinding
import com.higana.neating.io.MyApiAdapter
import com.higana.neating.model.FullResponse
import com.higana.neating.model.SpoonRecipeInformation
import com.higana.neating.ui.adapter.RecyclerRecipesListAdapter
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecipesActivity : Activity() {


    private lateinit var binding: RecipesFragmentBinding
    private lateinit var mainIngredient: String
    private val myApiAdapter = MyApiAdapter()
    private var myData: ArrayList<SpoonRecipeInformation> =
        ArrayList()
    private lateinit var recipes: ArrayList<SpoonRecipeInformation>
    private lateinit var adapter: RecyclerRecipesListAdapter


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding = RecipesFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchView.queryHint = getString(R.string.search_recipes)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                hideKeyboard(this@RecipesActivity)
                mainIngredient = query
                recipes = getRecipes()
                return true
            }

        })
    }


    fun getRecipes(): ArrayList<SpoonRecipeInformation> {
        val call = myApiAdapter.getMyApiService(applicationContext)
            .complexSearchRecipe(app_key = getString(R.string.app_key), q = mainIngredient)
        doAsync {

            call.enqueue(object : Callback<FullResponse> {
                override fun onFailure(call: Call<FullResponse>, t: Throwable) {
                    binding.noResultsFound.visibility = View.VISIBLE
                }

                override fun onResponse(
                    call: Call<FullResponse>,
                    response: Response<FullResponse?>
                ) {
                    binding.noResultsFound.visibility = View.GONE
                    myData.clear()
                    if (response.isSuccessful) {
                        myData.addAll(response.body()!!.results)
                        if (!myData.isEmpty())
                            displayRecyclerView()
                        else
                            binding.noResultsFound.visibility = View.VISIBLE
                    }

                }


            })
        }
        return myData
    }

    fun displayRecyclerView() {
        adapter = RecyclerRecipesListAdapter(myData)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.visibility = View.VISIBLE
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

}