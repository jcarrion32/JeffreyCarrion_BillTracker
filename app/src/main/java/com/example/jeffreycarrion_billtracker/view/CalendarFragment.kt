package com.example.jeffreycarrion_billtracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jeffreycarrion_billtracker.databinding.FragmentCalendarPageBinding

class CalendarFragment: ViewModelFragment(){

    private lateinit var binding: FragmentCalendarPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarPageBinding.inflate(layoutInflater)

        binding.btnBills.setOnClickListener{
            findNavController().navigate(
                CalendarFragmentDirections.actionCalendarFragmentToBillFragment()
            )
        }

        binding.btnCalendarEvent.setOnClickListener{
            findNavController().navigate(
                CalendarFragmentDirections.actionCalendarFragmentToEventFragment()
            )
        }

        return binding.root
    }
}