package com.example.eval4

import com.example.eval4.model.Item

class Data {
    companion object {
        var dataList = listOf<Item>(
            Item(R.drawable.lightning1, "100 Credits Prize/Ticket Game Card"),
            Item(R.drawable.lightning1, "Card with coke"),
            Item(R.drawable.lightning1, "New Birthday Card"),
            Item(R.drawable.lightning1, "Birthday game card with Discount"),
            Item(R.drawable.lightning1, "Birthday game card with 10% discount")
        )
    }

    fun getData():List<Item>{
        return dataList;
    }

}