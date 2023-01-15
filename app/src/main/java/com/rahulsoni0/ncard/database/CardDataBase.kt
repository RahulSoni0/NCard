package com.rahulsoni0.ncard.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rahulsoni0.ncard.model.CardModel


@Database(
    entities = [CardModel::class],
    version = 1,
    exportSchema = false
)
abstract class CardDatabase : RoomDatabase() {

    abstract fun getCardDao(): DAO

    companion object { //singleton pattern , only one instance at anytime....

        @Volatile
        private var instance: CardDatabase? = null
        private val LOCK = Any() //only one thread can use it in background..

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CardDatabase::class.java,
            "card_database"
        ).build()
    }

}