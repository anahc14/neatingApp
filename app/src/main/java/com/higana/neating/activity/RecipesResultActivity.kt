package com.higana.neating.activity

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.higana.neating.databinding.RecyclerViewBinding
import com.higana.neating.model.ResponseModel
import com.higana.neating.ui.adapter.RecyclerAdapter

class RecipesResultActivity : Activity() {

    private lateinit var binding: RecyclerViewBinding
    private lateinit var adapter: RecyclerAdapter
    private var myDataSet: ArrayList<ResponseModel.RecipeInformation> = (
            ArrayList())
    private val recyclerAdapter: RecyclerAdapter = RecyclerAdapter(this.myDataSet)
    private lateinit var myList: ArrayList<ResponseModel.RecipeInformation>

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        binding = RecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = RecyclerAdapter(myDataSet)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        myList =
            intent.getSerializableExtra("recipes") as ArrayList<ResponseModel.RecipeInformation>
    }

    override fun onStart() {
        super.onStart()
        if (myDataSet.size == 0) {
            recyclerAdapter.addAllItems(myList)
        }
    }
}