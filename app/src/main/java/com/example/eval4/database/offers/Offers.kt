package com.example.eval4.database.offers

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eval4.R

@Entity(tableName = "offers")
data class Offers(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val image:Int = R.drawable.lightning1,
    val title:String,
    var isSelected : Boolean = false
)
