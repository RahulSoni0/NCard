package com.rahulsoni0.ncard.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rahulsoni0.ncard.database.CardDatabase
import com.rahulsoni0.ncard.databinding.ActivityMainBinding
import com.rahulsoni0.ncard.repository.CardRepository
import com.rahulsoni0.ncard.viewModel.CardActivityViewModel
import com.rahulsoni0.ncard.viewModel.CardActivityViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var cardActivityViewModel: CardActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)

        try {
            setContentView(binding.root)
            val cardRepository = CardRepository(CardDatabase(this))
            val cardActivityViewModelFactory = CardActivityViewModelFactory(cardRepository)
            cardActivityViewModel = ViewModelProvider(
                this,
                cardActivityViewModelFactory
            )[cardActivityViewModel::class.java]
        } catch (e: Exception) {
            Log.d("TAG", "onCreate: ")
        }


    }
}