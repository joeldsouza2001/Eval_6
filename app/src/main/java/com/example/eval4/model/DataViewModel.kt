package com.example.eval4.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DataViewModel : ViewModel() {

    private var _offersList = listOf<Item>(
        Item(text = "100 Credits Prize/Ticket Game Card"),
        Item(text = "Card with coke"),
        Item(text = "New Birthday Card"),
        Item(text = "Birthday game card with Discount"),
        Item(text = "Birthday game card with 10% discount")
    )

    val offersList get() = _offersList

    val offersSelected get() = _offersList.filter { it.selected }

    private var _offersSelectedSize = MutableLiveData<Int>(0)
    val offersSelectedSize get() = _offersSelectedSize


    fun toggleSelect(pos: Int) {
        _offersList[pos].selected = !_offersList[pos].selected
        updateSize()
    }

    fun updateSize() {
        _offersSelectedSize.value = offersSelected.size
    }

}