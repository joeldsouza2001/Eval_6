package com.example.eval4.database.otp

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CheckVerifiedDao {

    @Upsert
    suspend fun insert(checkVerified: CheckVerified)


    @Query("select * from check_verified where device='this' LIMIT 1")
    suspend fun check() : CheckVerified?
}