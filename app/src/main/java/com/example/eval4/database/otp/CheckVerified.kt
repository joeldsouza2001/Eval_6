package com.example.eval4.database.otp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "check_verified")
data class CheckVerified(

    @PrimaryKey(autoGenerate = false)
    val device:String,
    val isVerified:Boolean = false
)
