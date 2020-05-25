package com.higana.neating.activity

import android.app.Activity
import android.os.Bundle
import androidx.annotation.Nullable
import com.higana.neating.databinding.RecipesFragmentBinding

class RecipesActivity : Activity(){
    private lateinit var binding: RecipesFragmentBinding

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipesFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}