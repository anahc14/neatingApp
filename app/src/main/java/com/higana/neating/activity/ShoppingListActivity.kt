package com.higana.neating.activity

import android.app.Activity
import android.os.Bundle
import androidx.annotation.Nullable
import com.higana.neating.databinding.ShoppingListFragmentBinding

class ShoppingListActivity : Activity() {

    private lateinit var binding: ShoppingListFragmentBinding

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShoppingListFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}