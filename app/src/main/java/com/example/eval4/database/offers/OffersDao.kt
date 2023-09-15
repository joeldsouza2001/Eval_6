package com.example.eval4.database.offers

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface OffersDao {

    @Insert
    suspend fun insert(offers: Offers)

    @Query("select * from offers")
    suspend fun fetch() : List<Offers>

    @Update
    suspend fun update(offer: Offers)

    @Query("select * from offers where isSelected='1'")
    suspend fun fetchSelected():List<Offers>
}