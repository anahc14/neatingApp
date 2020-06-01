package com.higana.neating.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.higana.neating.R
import com.higana.neating.inflate
import com.higana.neating.model.Ingredients
import kotlinx.android.synthetic.main.ingredients_recyclerview_item_row.view.*
import java.util.*

class RecyclerIngredientsAdapter(
    private val ingredients: ArrayList<Ingredients>
) : RecyclerView.Adapter<RecyclerIngredientsAdapter.CustomHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerIngredientsAdapter.CustomHolder {
        val inflatedView = parent.inflate(R.layout.ingredients_recyclerview_item_row, false)
        return CustomHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: RecyclerIngredientsAdapter.CustomHolder, position: Int) {
        val itemIngredient = ingredients[position]
        holder.bindIngredients(itemIngredient)
    }


    class CustomHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var ingredients: Ingredients? = null


        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            //nothing to do with onclick
        }

        fun bindIngredients(ingredients: Ingredients) {
            this.ingredients = ingredients

            view.ingredient_name.text = ingredients.name
            view.ingredient_measures.text =String.format(ingredients.measures.metric.amount.toString() +  " " + ingredients.measures.metric.unitShort)

        }

        companion object {
            private val RECIPE_KEY = "RECIPE"
        }
    }


}