package com.higana.neating.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.higana.neating.R
import com.higana.neating.model.ResponseModel


class MyDataAdapter() :
    RecyclerView.Adapter<MyDataAdapter.MyViewHolder>() {

    private var myDataset: ArrayList<ResponseModel.RecipeInformation> =
        ArrayList<ResponseModel.RecipeInformation>()

    class MyViewHolder(val textView: TextView, val yieldView: TextView) :
        RecyclerView.ViewHolder(textView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        // create a new view
        val titleView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_view, parent, false) as TextView
        // set the view's size, margins, paddings and layout parameters
        titleView.setPadding(10, 10, 10, 10)

        val yieldView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view, parent, false) as TextView


        return MyViewHolder(
            titleView,
            yieldView
        )
    }

    fun setDataSet(dataSet: ArrayList<ResponseModel.RecipeInformation>) {
        myDataset = dataSet
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = myDataset[position].recipe.label
        holder.yieldView.text = myDataset[position].recipe.yield
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

}
