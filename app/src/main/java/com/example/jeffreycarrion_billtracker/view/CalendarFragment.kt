package com.example.jeffreycarrion_billtracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jeffreycarrion_billtracker.databinding.FragmentCalendarPageBinding

class CalendarFragment: ViewModelFragment(){

    private val args: CalendarFragmentArgs by navArgs()

//    private val incomeSetup by lazy {
//        IncomeFragment.sharedPreferences
//    }

    private lateinit var binding: FragmentCalendarPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarPageBinding.inflate(layoutInflater)




        binding.btnBills.setOnClickListener{
            findNavController().navigate(
                CalendarFragmentDirections.actionCalendarFragmentToBillFragment(args.incomeAmount,args.incomePayday, args.incomeFreq)
            )
        }

        binding.btnCalendarEvent.setOnClickListener{
            findNavController().navigate(
                CalendarFragmentDirections.actionCalendarFragmentToEventFragment(args.incomeAmount, args.incomePayday, args.incomeFreq)
            )
        }

        binding.btnBackIncome.setOnClickListener {
            findNavController().navigate(
                CalendarFragmentDirections.actionCalendarFragmentToIncomeFragment()
            )
        }

//        if (incomeSetup != null){
//        var incomeFreq = incomeSetup.getString("method","incomeFreq")
//        var incomeAmount = incomeSetup.getString("double","incomeAmount")
//        var incomePayDay = incomeSetup.getString("day","incomePayDay")}




        return binding.root
    }
}