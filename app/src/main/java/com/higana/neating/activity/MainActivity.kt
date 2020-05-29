package com.higana.neating.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.higana.neating.databinding.MainCardsBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainCardsBinding

    @SuppressLint("InflateParams")
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calendarCardView.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        binding.recipesCardView.setOnClickListener {
            val intent = Intent(this, RecipesActivity::class.java)
            startActivity(intent)
        }

        binding.shoppingListCardView.setOnClickListener {
            val intent = Intent(this, ShoppingListActivity::class.java)
            startActivity(intent)
        }
    }
}

