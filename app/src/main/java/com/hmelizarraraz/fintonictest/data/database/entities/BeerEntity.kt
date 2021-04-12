package com.hmelizarraraz.fintonictest.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Beer Entity Model used for ORM (Room)
 */
@Entity(tableName = "beers")
data class BeerEntity(
    @field:PrimaryKey val id: Int,
    val name: String,
    val isFavorite: Boolean,
    val imageUrl: String,
    val description: String
)
