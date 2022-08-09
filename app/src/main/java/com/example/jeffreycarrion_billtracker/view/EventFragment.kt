package com.example.jeffreycarrion_billtracker.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jeffreycarrion_billtracker.databinding.FragmentEventPageBinding
import com.example.jeffreycarrion_billtracker.model.database.BillEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventFragment: ViewModelFragment() {

    private lateinit var binding: FragmentEventPageBinding


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
            //val amountDue = binding.etAmountDue.text.toString().format("%.2f").toDouble()

            var amountDue = String.format("%.2f", binding.etAmountDue.text.toString().toDouble()).toDouble()
            var dueDate = binding.ddPayday.dayOfMonth.toString()

            val account = BillEntity(0, billName, dueDate, amountDue)

            viewModel.insertNewBill(account)

            findNavController().navigate(
                EventFragmentDirections.actionEventFragmentToBillFragment()
            )
        }

        binding.btnBackBill.setOnClickListener{
            findNavController().navigate(
                EventFragmentDirections.actionEventFragmentToBillFragment()
            )
        }

        return binding.root
    }

//    companion object {
//        const val EXTRA_REPLY = "com.example.roomcodelab.REPLY"
//    }

}