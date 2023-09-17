package com.example.eval4.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eval4.R
import com.example.eval4.adapter.ItemAdapterHome

import com.example.eval4.adapter.ItemAdapterSelected
import com.example.eval4.database.offers.OffersDatabase
import com.example.eval4.databinding.FragmentSelectedBinding
import com.example.eval4.model.DataViewModel
import com.example.eval4.model.DataViewModelFactory

class SelectedFragment : Fragment() {
    private lateinit var sharedViewModel : DataViewModel

    private lateinit var _binding : FragmentSelectedBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSelectedBinding.inflate(inflater,container,false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        val recyclerView: RecyclerView = binding.recyclerView;


        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager


        sharedViewModel.offersList.observe(viewLifecycleOwner,{
            var off = it?.filter { it.isSelected }
//            Log.i("ee","${off}")

            recyclerView.adapter = off?.let { it1 -> ItemAdapterSelected(requireContext(), it1) }


        })

        val spinner = binding.spinner

        val adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.items, android.R.layout.simple_spinner_item)

        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinner.adapter = adapter
    }
}