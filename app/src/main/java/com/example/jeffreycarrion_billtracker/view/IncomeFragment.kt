package com.example.jeffreycarrion_billtracker.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import com.example.jeffreycarrion_billtracker.databinding.FragmentIncomePageBinding
import java.util.*


class IncomeFragment : ViewModelFragment() {


    private lateinit var binding: FragmentIncomePageBinding
    private lateinit var incomeAmount: String
    private lateinit var incomeFreq: String
    private lateinit var incomePayDay: String
    private lateinit var incomePayMonth: String
    private lateinit var incomePayYear: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentIncomePageBinding.inflate(layoutInflater)
        val sharedPreferences = this.activity?.getSharedPreferences("INCOME", Context.MODE_PRIVATE)
        val today = Calendar.getInstance()

        if (sharedPreferences?.all!!.isNotEmpty()) {
            updateIncomeLayout(sharedPreferences, today)
        }else{
            binding.rgIncomeFreq.check(binding.rbWeekly.id)
        }


        binding.btnClearIncome.setOnClickListener {
            sharedPreferences.edit {
                this.clear()

            }
            clearField()
        }

        binding.btnAddIncome.setOnClickListener {
            incomeFreq = when (binding.rgIncomeFreq.checkedRadioButtonId) {
                binding.rbWeekly.id -> "weekly"
                binding.rbBiweekly.id -> "biweekly"

                else -> "twice"
            }
            incomePayDay = binding.ddIncomePayday.dayOfMonth.toString()
            incomePayMonth = binding.ddIncomePayday.month.toString()
            incomePayYear = binding.ddIncomePayday.year.toString()

            incomeAmount = binding.etIncome.text.toString()

            var savedDate = today.clone() as Calendar
            savedDate.set(incomePayYear.toInt(), incomePayMonth.toInt(), incomePayDay.toInt())

            savedDate = updatePayDay(incomeFreq, savedDate)

            val incomeSetup = sharedPreferences.edit()
            incomeSetup?.putString("method", incomeFreq)
            incomeSetup?.putString("double", incomeAmount)
            incomeSetup?.putString("day", savedDate.get(Calendar.DAY_OF_MONTH).toString())
            incomeSetup?.putString("month", savedDate.get(Calendar.MONTH).toString())
            incomeSetup?.putString("year", savedDate.get(Calendar.YEAR).toString())
            incomeSetup?.commit()


            findNavController().navigate(
                IncomeFragmentDirections.actionIncomeFragmentToCalendarFragment(
                    incomeAmount,
                    savedDate.get(Calendar.DAY_OF_MONTH).toString(),
                    incomeFreq
                )
            )
        }

        binding.btnExit.setOnClickListener {
            findNavController().navigate(
                IncomeFragmentDirections.actionIncomeFragmentToLoginFragment()
            )
        }


        return binding.root
    }

    private fun updateIncomeLayout(sharedPreferences: SharedPreferences, today: Calendar) {
        binding.etIncome.setText(sharedPreferences.getString("double", ""))
        binding.ddIncomePayday.updateDate(
            sharedPreferences.getString("year", today.get(Calendar.YEAR).toString())!!.toInt(),
            sharedPreferences.getString("month", today.get(Calendar.MONTH).toString())!!.toInt(),
            sharedPreferences.getString("day", today.get(Calendar.DAY_OF_MONTH).toString())!!.toInt()
        )

        when (sharedPreferences.getString("method", "weekly")) {
            "weekly" -> binding.rgIncomeFreq.check(binding.rbWeekly.id)
            "biweekly" -> binding.rgIncomeFreq.check(binding.rbBiweekly.id)

            else -> binding.rgIncomeFreq.check(binding.rbTwiceMonth.id)
        }
    }

    private fun clearField() {
        val today = Calendar.getInstance()
        binding.etIncome.setText("")
        binding.ddIncomePayday.updateDate(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )
        binding.rgIncomeFreq.check(binding.rbWeekly.id)
    }


    private fun updatePayDay(
        method: String,
        payDay: Calendar
    ): Calendar {

        val updatedPay = when (method) {
            "weekly" -> payDayUpdateWeekly(payDay)
            "biweekly" -> payDayUpdateBiweekly(payDay)

            else -> payDayUpdateTwice(payDay)
        }
        return updatedPay

    }

    private fun payDayUpdateWeekly(payDay: Calendar): Calendar {
        val today = Calendar.getInstance()

        today.set(Calendar.HOUR_OF_DAY, 0)
        while (today > payDay) {
            payDay.add(Calendar.DAY_OF_MONTH, 7)
        }

        return payDay
    }


    private fun payDayUpdateBiweekly(
        payDay: Calendar
    ): Calendar {
        val today = Calendar.getInstance()

        today.set(Calendar.HOUR_OF_DAY, 0)
        while (today[Calendar.DAY_OF_MONTH] > payDay[Calendar.DAY_OF_MONTH]) {
            payDay.add(Calendar.DAY_OF_MONTH, 14)
        }

        return payDay
    }

    private fun payDayUpdateTwice(payDay: Calendar): Calendar{
        val today = Calendar.getInstance()

        payDay.set(Calendar.DAY_OF_MONTH, 15)
        today.set(Calendar.HOUR_OF_DAY, 0)
        while (today > payDay) {
            if (payDay[Calendar.DAY_OF_MONTH] == 15) {
                payDay.set(
                    Calendar.DAY_OF_MONTH,
                    payDay.getActualMaximum(Calendar.DAY_OF_MONTH)
                )
                if (payDay.get(Calendar.DAY_OF_MONTH) == 31) {
                    payDay.add(Calendar.DAY_OF_MONTH, -1)
                }
            } else {
                payDay.add(
                    Calendar.DAY_OF_MONTH,
                    2
                )
                payDay.set(
                    Calendar.DAY_OF_MONTH,
                    15
                )
            }
        }
        return payDay
    }
}