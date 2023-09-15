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
    val database : OffersDao,
     application: Application
) : AndroidViewModel(application) {

    private var _offersList :MutableLiveData<List<Offers>> = MutableLiveData<List<Offers>>()
//        Item(text = "100 Credits Prize/Ticket Game Card"),
//        Item(text = "Card with coke"),
//        Item(text = "New Birthday Card"),
//        Item(text = "Birthday game card with Discount"),
//        Item(text = "Birthday game card with 10% discount")
//    )

    val offersList get() = _offersList

private var _os : MutableLiveData<List<Offers>> = MutableLiveData<List<Offers>>()
    val os get() = _os
    val offersSelected get() = _offersList.value!!.filter { it.isSelected }

    private var _offersSelectedSize = MutableLiveData<Int>()
    val offersSelectedSize get() = _offersSelectedSize

    init {
        _offersSelectedSize.value = 0
        fetchAll()
        fetchSelected()
//        updateSize()
//        _offersSelectedSize.value = _os.value!!.filter { it.isSelected }.size


    }




    fun toggleSelect(offer: Offers) {
//        _offersList[pos].selected = !_offersList[pos].selected
        Log.i("t","${offer}")

//
        viewModelScope.launch{
            withContext(Dispatchers.Main){
                database.update(Offers(offer.id,title=offer.title, isSelected = !offer.isSelected))

//                updateSize()
//                fetchAll()
                fetchSelected()
            }

        }


    }

//    fun updateSize() {
//        _offersSelectedSize.value = offersSelected.size
//
//
//    }

    fun insert( title:String){

        viewModelScope.launch {
            database.insert(Offers(title=title, isSelected = false))
            withContext(Dispatchers.Main){
            fetchAll()
            }
        }


    }

    fun fetchAll(){
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                val lst = database.fetch()
                _offersList.value = lst
//                _offersSelectedSize.value = _os.value!!.filter { it.isSelected }.size


            }

        }
    }

    fun fetchSelected(){
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                val lst = database.fetchSelected()
                _os.value = lst
                _offersSelectedSize.value = _os.value!!.filter { it.isSelected }.size

                Log.i("aa","${_os.value}")
            }


        }

    }




}