package com.rahulsoni0.ncard.model

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CardModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val content: String,
    val date: String,
    val color: Int = Color.WHITE


) : Serializable //by this we can directly pass this model class as object
