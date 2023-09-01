package com.example.eval4


import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eval4.databinding.FragmentOtpVerificationBinding


class OtpVerificationFragment : Fragment() {
        private  lateinit var _binding:FragmentOtpVerificationBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOtpVerificationBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var editList = listOf<EditText>(binding.editText,binding.editText2,binding.editText3,binding.editText4,binding.editText5,binding.editText6)

        super.onViewCreated(view, savedInstanceState)

        editList.forEach{it.inputType = InputType.TYPE_CLASS_NUMBER}

        binding.button.setOnClickListener{
            var goToNext :Boolean = true;

            for(i in editList){
                goToNext = goToNext && i.text.isNotEmpty()

                }
            if(goToNext) {
                it.findNavController()
                    .navigate(R.id.action_otpVerificationFragment_to_otpVerifiedFragment)
            }

        }
    }
}