package com.higana.neating.ui.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.higana.neating.R
import com.higana.neating.activity.RecipeInformationActivity
import com.higana.neating.inflate
import com.higana.neating.model.SpoonRecipeInformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(
    private val recipesInfo: ArrayList<SpoonRecipeInformation>
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

    fun addAllItems(items: ArrayList<SpoonRecipeInformation>) {
        recipesInfo.addAll(items)
        notifyDataSetChanged()
    }

    class CustomHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var recipesInfo: SpoonRecipeInformation? = null
        private var recipesImage: String? = ""

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context
            val showRecipeIntent = Intent(context, RecipeInformationActivity::class.java)
            showRecipeIntent.putExtra(RECIPE_KEY, recipesInfo)
            context.startActivity(showRecipeIntent)
        }

        fun bindRecipe(recipe: SpoonRecipeInformation) {
            this.recipesInfo = recipe

            Picasso.get().load(recipe.image).resize(800,450).centerCrop().into(view.recipe_image);
            view.recipe_title.text = recipe.title
            view.recipe_calories.text = recipe.calories.toString()
        }

        companion object {
            private val RECIPE_KEY = "RECIPE"
        }
    }
}

