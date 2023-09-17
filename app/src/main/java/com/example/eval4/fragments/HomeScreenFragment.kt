package com.example.eval4.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eval4.R
import com.example.eval4.adapter.ItemAdapterHome
import com.example.eval4.database.offers.Offers
import com.example.eval4.database.offers.OffersDatabase
import com.example.eval4.database.otp.CheckVerifiedDatabase
import com.example.eval4.databinding.FragmentHomeScreenBinding
import com.example.eval4.model.CheckOtpViewModel
import com.example.eval4.model.CheckOtpViewModelFactory
import com.example.eval4.model.DataViewModel
import com.example.eval4.model.DataViewModelFactory

class HomeScreenFragment : Fragment() {
    private lateinit var _binding: FragmentHomeScreenBinding
    private lateinit var sharedViewModel: DataViewModel
    val binding get() = _binding

    lateinit var continueBtn: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("ServiceCast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


        val recyclerView: RecyclerView = binding.recyclerView;
        continueBtn = binding.continueBtn
        val application = requireNotNull(requireActivity()).application
        val dataSource = OffersDatabase.getInstance(application).offersDao
        val viewModelFactory = DataViewModelFactory(dataSource, application)
        sharedViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(DataViewModel::class.java)

        binding?.apply {
            sharedViewModel = sharedViewModel
        }

//        sharedViewModel.fetchAll()
//        sharedViewModel.fetchSelected()


        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager

        sharedViewModel.offersList.observe(viewLifecycleOwner, {
            recyclerView.adapter = ItemAdapterHome(requireContext(), it, ::toggleItemSelect)
//            sharedViewModel.updateSize()
        })

//        recyclerView.adapter = ItemAdapterHome(requireContext(),sharedViewModel.offersList.value!!,::toggleItemSelect)


//        val spinner = binding.spinner
//
//        val adapter = ArrayAdapter.createFromResource(requireContext(),
//            R.array.items, android.R.layout.simple_spinner_item)
//
//        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


//        spinner.adapter = adapter


        continueBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_selectedFragment)
        }

        binding.submitBtn.setOnClickListener {
            sharedViewModel.insert(binding.titleEdit.text.toString())
            binding.titleEdit.text.clear()
            imm.hideSoftInputFromWindow(binding.submitBtn.windowToken, 0)
        }


        sharedViewModel.offersSelectedSize.observe(viewLifecycleOwner, Observer {
            if (it > 0)
                continueBtn.visibility = View.VISIBLE
            else
                continueBtn.visibility = View.GONE

        })

    }

    private fun toggleItemSelect(offer: Offers, pos: Int): Unit {
        sharedViewModel.toggleSelect(offer, pos)
    }


}