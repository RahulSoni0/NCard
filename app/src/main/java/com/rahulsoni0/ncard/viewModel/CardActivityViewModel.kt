package com.rahulsoni0.ncard.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsoni0.ncard.model.CardModel
import com.rahulsoni0.ncard.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class CardActivityViewModel(private val repository: CardRepository) : ViewModel(){


    fun saveCard(newCard: CardModel) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCard(newCard)
        }

    fun updateCard(exsistingCard: CardModel) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCard(exsistingCard)
        }

    fun deleteCard(existingCard: CardModel) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCard(existingCard)
        }

    fun searchCard(query: String): LiveData<List<CardModel>> {
        return repository.searchCard(query)
    }

    fun getAllCards(): LiveData<List<CardModel>> {
        return repository.getCards()
    }

}