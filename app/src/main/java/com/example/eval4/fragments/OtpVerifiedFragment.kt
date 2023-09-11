package com.example.eval4.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.eval4.R
import com.example.eval4.databinding.FragmentOtpVerifiedBinding

class OtpVerifiedFragment : Fragment() {

    private lateinit var _binding: FragmentOtpVerifiedBinding;
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOtpVerifiedBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener {
            it.findNavController().navigate(R.id.action_otpVerifiedFragment_to_homeScreenFragment)
        }
    }


}