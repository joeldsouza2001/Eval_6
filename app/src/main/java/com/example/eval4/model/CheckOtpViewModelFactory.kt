package com.example.eval4.model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eval4.database.otp.CheckVerifiedDao

class CheckOtpViewModelFactory(
    private val dataSource: CheckVerifiedDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckOtpViewModel::class.java)) {
            return CheckOtpViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}