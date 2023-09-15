package com.example.eval4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.eval4.database.otp.CheckVerifiedDatabase
import com.example.eval4.databinding.ActivityMainBinding
import com.example.eval4.model.CheckOtpViewModel
import com.example.eval4.model.CheckOtpViewModelFactory

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var checkOtpViewModel: CheckOtpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = this.findNavController(R.id.navHostFragment)

        NavigationUI.setupActionBarWithNavController(this, navController)


        val application = requireNotNull(this).application
        val dataSource = CheckVerifiedDatabase.getInstance(application).checkVerifiedDao
        val viewModelFactory = CheckOtpViewModelFactory(dataSource, application)
        checkOtpViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(CheckOtpViewModel::class.java)
        checkOtpViewModel.checkOtpVerified()

checkOtpViewModel.otpVerified.observe(this, Observer {
    if(it){
        navController.navigate(R.id.homeScreenFragment)
    }
    else{
        navController.navigate(R.id.otpVerificationFragment)

    }
})







//
//        if (checkOtpViewModel.otpVerified != null) {
//            if (checkOtpViewModel.otpVerified.value!!) {
//                navController.navigate(R.id.homeScreenFragment)
//
//            }
//            Log.i("Check", "a : ${checkOtpViewModel.otpVerified != null}")
////                Log.i("Check","b : ${checkOtpViewModel.otpVerified!!.isVerified}")
//
//        } else {
//            navController.navigate(R.id.otpVerificationFragment)
//            Log.i("Check", "a : ${checkOtpViewModel.otpVerified != null}")
////                Log.i("Check","b : ${checkOtpViewModel.otpVerified!!.isVerified}")
//        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return navController.navigateUp()
    }

//    suspend fun check(){
//        if(checkOtpViewModel.otpVerified !=null){
//            if(checkOtpViewModel.otpVerified!!.isVerified){
//                navController.navigate(R.id.homeScreenFragment)
//
//            }
//            Log.i("Check","a : ${checkOtpViewModel.otpVerified !=null}")
////                Log.i("Check","b : ${checkOtpViewModel.otpVerified!!.isVerified}")
//
//        }
//        else{
//            navController.navigate(R.id.otpVerificationFragment)
//            Log.i("Check","a : ${checkOtpViewModel.otpVerified !=null}")
////                Log.i("Check","b : ${checkOtpViewModel.otpVerified!!.isVerified}")
//        }
//    }

}