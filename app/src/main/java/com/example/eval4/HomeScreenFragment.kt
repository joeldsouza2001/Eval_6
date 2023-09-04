package com.example.eval4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eval4.adapter.ItemAdapterHome
import com.example.eval4.databinding.FragmentHomeScreenBinding
import com.example.eval4.model.Item

class HomeScreenFragment : Fragment() {
private lateinit var _binding : FragmentHomeScreenBinding
     val binding get() = _binding

    lateinit var continueBtn : Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerView;
        continueBtn = binding.continueBtn


        val data: List<Item> = Data().getData()

//        val ctx = context!!

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ItemAdapterHome(requireContext(),data,binding.continueBtn)

        val spinner = binding.spinner

        val adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.items, android.R.layout.simple_spinner_item)

        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinner.adapter = adapter


        continueBtn.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_selectedFragment)
        }





    }

}