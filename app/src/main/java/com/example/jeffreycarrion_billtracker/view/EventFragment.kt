package com.example.jeffreycarrion_billtracker.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cesarferreira.tempo.Tempo
import com.example.jeffreycarrion_billtracker.databinding.FragmentEventPageBinding
import com.example.jeffreycarrion_billtracker.model.database.BillEntity

class EventFragment: ViewModelFragment() {

    private lateinit var binding: FragmentEventPageBinding
    private val args: EventFragmentArgs by navArgs()
    private var today = Tempo.now


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventPageBinding.inflate(layoutInflater)

//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
//        val formatted = current.format(formatter)

         //today's date


        binding.btnAddAccount.setOnClickListener{

            val billName = binding.etBillName.text.toString()

            val amountDue = String.format("%.2f", binding.etAmountDue.text.toString().toDouble()).toDouble()
            val dueDate = binding.ddPayday.dayOfMonth.toString()

            val account = BillEntity(0, billName, dueDate, amountDue)

            viewModel.insertNewBill(account)

            findNavController().navigate(
                EventFragmentDirections.actionEventFragmentToBillFragment(args.incomeAmount, args.incomePayday, args.incomeFreq)
            )
        }

        binding.btnBackBill.setOnClickListener{
            findNavController().navigate(
                EventFragmentDirections.actionEventFragmentToBillFragment(args.incomeAmount, args.incomePayday, args.incomeFreq)
            )
        }

        return binding.root
    }



}