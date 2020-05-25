package com.higana.neating.activity

import android.app.Activity
import android.os.Bundle
import androidx.annotation.Nullable
import com.higana.neating.databinding.CalendarFragmentBinding

class CalendarActivity : Activity() {

    private lateinit var binding: CalendarFragmentBinding

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}