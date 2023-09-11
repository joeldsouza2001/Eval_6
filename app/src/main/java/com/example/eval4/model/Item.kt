package com.example.eval4.model

import com.example.eval4.R

data class Item(
    val image : Int = R.drawable.lightning1,
    val text : String,
    var selected:Boolean = false
)