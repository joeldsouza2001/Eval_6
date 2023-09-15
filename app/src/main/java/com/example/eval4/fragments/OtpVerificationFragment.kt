package com.example.eval4.fragments


import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.eval4.databinding.FragmentOtpVerificationBinding
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.example.eval4.R
import com.example.eval4.database.otp.CheckVerifiedDatabase
import com.example.eval4.model.CheckOtpViewModel
import com.example.eval4.model.CheckOtpViewModelFactory


class OtpVerificationFragment : Fragment() {
        private  lateinit var _binding:FragmentOtpVerificationBinding
        private lateinit var checkOtpViewModel : CheckOtpViewModel
    private val binding get() = _binding!!




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOtpVerificationBinding.inflate(inflater,container,false)
        val application = requireNotNull(requireActivity()).application
        val dataSource = CheckVerifiedDatabase.getInstance(application).checkVerifiedDao
        val viewModelFactory = CheckOtpViewModelFactory(dataSource, application)
         checkOtpViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(CheckOtpViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var editList = listOf<EditText>(binding.editText,binding.editText2,binding.editText3,binding.editText4,binding.editText5,binding.editText6)
        for (i in 0 until editList.size-1) {
            val currentEditText = editList[i]
            val nextEditText = editList[i+1]
            currentEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1 ) {
                        nextEditText.requestFocus()
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}    })
        }
        super.onViewCreated(view, savedInstanceState)

        editList.forEach{it.inputType = InputType.TYPE_CLASS_NUMBER}

        binding.button.setOnClickListener{
            var goToNext :Boolean = true;

            for(i in editList){
                goToNext = goToNext && i.text.isNotEmpty()

                }
            if(goToNext) {

            if(!checkOtpViewModel.otpVerified.value!!){
                checkOtpViewModel.upsert()
            }

                it.findNavController()

                    .navigate(R.id.action_otpVerificationFragment_to_otpVerifiedFragment)
            }

        }


    }
}