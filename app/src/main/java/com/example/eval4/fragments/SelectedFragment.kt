package com.example.eval4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eval4.R

import com.example.eval4.adapter.ItemAdapterSelected
import com.example.eval4.databinding.FragmentSelectedBinding
import com.example.eval4.model.DataViewModel

class SelectedFragment : Fragment() {
    private val sharedViewModel : DataViewModel by activityViewModels()

    private lateinit var _binding : FragmentSelectedBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectedBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            sharedViewModel = sharedViewModel
        }
        val recyclerView: RecyclerView = binding.recyclerView;


        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ItemAdapterSelected(requireContext(),sharedViewModel.offersSelected)

        val spinner = binding.spinner

        val adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.items, android.R.layout.simple_spinner_item)

        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinner.adapter = adapter
    }
}