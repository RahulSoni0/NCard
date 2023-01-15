package com.rahulsoni0.ncard.repository

import com.rahulsoni0.ncard.database.CardDatabase
import com.rahulsoni0.ncard.model.CardModel

class CardRepository(private val db: CardDatabase) {


    fun getCards() = db.getCardDao().getAllCards()

    fun searchCard(query: String) = db.getCardDao().searchCard(query)
    suspend fun addCard(card: CardModel) = db.getCardDao().addCard(card)
    suspend fun deleteCard(card: CardModel) = db.getCardDao().deleteNote(card)
    suspend fun updateCard(card: CardModel) = db.getCardDao().updateCard(card)


}