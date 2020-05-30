package com.higana.neating.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.higana.neating.R
import com.higana.neating.inflate
import com.higana.neating.model.ResponseModel
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(
    private val recipesInfo: ArrayList<ResponseModel.RecipeInformation>
) : RecyclerView.Adapter<RecyclerAdapter.CustomHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.CustomHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return CustomHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return recipesInfo.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.CustomHolder, position: Int) {
        val itemRecipe = recipesInfo[position]
        holder.bindRecipe(itemRecipe)
    }

    fun addAllItems(items: ArrayList<ResponseModel.RecipeInformation>) {
        recipesInfo.addAll(items)
        notifyDataSetChanged()
    }

    class CustomHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var recipesInfo: ResponseModel.RecipeInformation? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
//            val context = itemView.context
//            val showRecipeIntent = Intent(context, RecipesActivity::class.java)
//            showRecipeIntent.putExtra(RECIPE_KEY, recipesInfo)
//            context.startActivity(showRecipeIntent)
        }

        fun bindRecipe(recipe: ResponseModel.RecipeInformation) {
            this.recipesInfo = recipe
            // Picasso.with(view.context).load(photo.url).into(view.itemImage)
            view.recipe_title.text = recipe.recipe.label
            // view.itemDescription.text = photo.explanation
        }

        companion object {
            private val RECIPE_KEY = "RECIPE"
        }
    }
}

