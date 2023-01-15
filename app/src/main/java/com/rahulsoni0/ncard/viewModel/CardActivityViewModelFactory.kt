package com.rahulsoni0.ncard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulsoni0.ncard.repository.CardRepository

class CardActivityViewModelFactory(private val repository: CardRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardActivityViewModel(repository) as T
    }


}