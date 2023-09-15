package com.example.eval4.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.eval4.database.otp.CheckVerified
import com.example.eval4.database.otp.CheckVerifiedDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CheckOtpViewModel(
    val database: CheckVerifiedDao,
    application: Application
) : AndroidViewModel(application)  {

    var otpVerified : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

init {
    otpVerified.value = false
}
     fun checkOtpVerified() : Boolean{

        viewModelScope.launch {
            withContext(Dispatchers.Main){
                var ins = database.check()
                if(ins!=null && ins.isVerified) {
                    otpVerified.value = true

                    Log.i("Check", "1${otpVerified.value}")
                }
            }


        }
         return otpVerified.value!!
    }

    fun upsert(){
        GlobalScope.launch {
            database.insert(CheckVerified("dev",isVerified = true))
        }
    }

}