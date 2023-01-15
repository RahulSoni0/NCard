package com.rahulsoni0.ncard.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rahulsoni0.ncard.model.CardModel

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCard(card: CardModel)

    @Update
    suspend fun updateCard(card: CardModel)

    @Query("SELECT * FROM CardModel ORDER BY id DESC")
    fun getAllCards(): LiveData<List<CardModel>>

    @Query("SELECT * FROM CardModel WHERE title LIKE :query OR content LIKE :query ORDER BY id DESC")
    fun searchCard(query: String): LiveData<List<CardModel>>

    @Delete
    suspend fun deleteNote(card: CardModel)


}