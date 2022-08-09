package com.example.jeffreycarrion_billtracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.jeffreycarrion_billtracker.databinding.FragmentBillPageBinding
import com.example.jeffreycarrion_billtracker.model.database.BillEntity

class BillFragment : ViewModelFragment() {
    private lateinit var binding: FragmentBillPageBinding

    private val billListAdapter by lazy {
        BillListAdapter(deleteBillFromList = ::deleteBillFromList)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBillPageBinding.inflate(layoutInflater)

        binding.rvBillList.apply {
            adapter = billListAdapter
        }

        binding.btnAddNewBill.setOnClickListener {
            findNavController().navigate(
                BillFragmentDirections.actionBillFragmentToEventFragment()
            )
        }

        binding.btnBackMain.setOnClickListener {
            findNavController().navigate(
                BillFragmentDirections.actionBillFragmentToCalendarFragment()
            )
        }

        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.allBill.observe(viewLifecycleOwner) { bills ->
            bills.let { billListAdapter.submitList(it as MutableList<BillEntity>) }
        }
    }

    private fun deleteBillFromList(id: Int){
        viewModel.deleteBill(id)
    }


}

//    inner class SwipeGesture: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//
//        override fun onMove(
//            recyclerView: RecyclerView,
//            viewHolder: RecyclerView.ViewHolder,
//            target: RecyclerView.ViewHolder
//        ): Boolean {
//
//            return false
//        }
//
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//            val position = viewHolder.adapterPosition
//            var account = billListAdapter.getAccAtPosition(position)
//
//            viewModel.deleteBill(account)
//        }
//    }