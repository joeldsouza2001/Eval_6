package com.example.eval4.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eval4.database.offers.Offers
import com.example.eval4.database.offers.OffersDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DataViewModel(
    val database: OffersDao,
    application: Application
) : AndroidViewModel(application) {

    private var _offersList: MutableLiveData<List<Offers>> = MutableLiveData<List<Offers>>()


    val offersList get() = _offersList

    private var _offersSelectedSize = MutableLiveData<Int>()
    val offersSelectedSize get() = _offersSelectedSize

    init {
        _offersSelectedSize.value = 0
        fetchAll()

    }


    fun toggleSelect(offer: Offers, pos: Int) {

        viewModelScope.launch {

            database.update(Offers(offer.id, title = offer.title, isSelected = !offer.isSelected))


        }
        _offersList.value?.get(pos)?.isSelected = !(_offersList.value?.get(pos)?.isSelected)!!
        updateSize()

    }


    fun updateSize() {
        _offersSelectedSize.value = _offersList.value?.filter { it.isSelected }?.size


    }

    fun insert(title: String) {
//        viewModelScope.launch {
//            database.delete()
//        }
        val newOffer = Offers(title = title, isSelected = false)

        viewModelScope.launch {
            database.insert(newOffer)
            val recentAdded = database.fetchRecent()
            _offersList.value = _offersList.value?.plus(recentAdded)

        }

    }



    fun fetchAll() {
        viewModelScope.launch {

            val lst = database.fetch()
            _offersList.value = lst
            updateSize()

        }

    }


}