package com.example.jeffreycarrion_billtracker.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jeffreycarrion_billtracker.databinding.BillItemBinding
import com.example.jeffreycarrion_billtracker.model.database.BillEntity

class BillListAdapter(
    private val deleteBillFromList: (Int) -> Unit
): ListAdapter<BillEntity, BillListAdapter.BillViewHolder>(BillCompare()) {

    class BillCompare: DiffUtil.ItemCallback<BillEntity>() {
        override fun areItemsTheSame(oldItem: BillEntity, newItem: BillEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BillEntity, newItem: BillEntity): Boolean {
            return oldItem.bill == newItem.bill
        }
    }

    inner class BillViewHolder (private val binding: BillItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun onBind(id: Int, bill: String?, amount: Double?, date: String?){
                val deleteBill = binding.btnDeleteBill

                binding.apply {
                    tvAccountName.text = bill
                    tvAmountDue.text = "$ " + String.format("%.2f", amount)
                    tvDueDate.text = date
                }

                binding.root.setOnClickListener{
                    if (deleteBill.visibility == View.GONE) deleteBill.visibility = View.VISIBLE
                    else deleteBill.visibility = View.GONE
                }

                deleteBill.setOnClickListener {
                    deleteBillFromList(id)
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        return BillViewHolder(
            BillItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        holder.onBind(getItem(position).id,getItem(position).bill, getItem(position).amount, getItem(position).date)
    }

    override fun submitList(list: MutableList<BillEntity>?) {
        super.submitList(list)
        Log.d("tag", "ListSubmit: $list")
    }



    fun getAccAtPosition(position: Int): Int{
        return getItem(position).id

    }
}