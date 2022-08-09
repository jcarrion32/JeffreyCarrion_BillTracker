package com.example.jeffreycarrion_billtracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jeffreycarrion_billtracker.model.database.BillEntity
import com.example.jeffreycarrion_billtracker.model.database.BillRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BillViewModel
    @Inject constructor
    (private val billRepo: BillRepo): ViewModel() {
    private val _allBills = MutableLiveData<List<BillEntity>>()

    val allBill: LiveData<List<BillEntity>> get()= _allBills

    init {
        queryAllBills()
    }

    private fun queryAllBills(){
        viewModelScope.launch {
            billRepo.allBill?.collect{
                _allBills.postValue(it)
            }
        }
    }

    fun insertNewBill(billEntity: BillEntity){
        viewModelScope.launch {
            billRepo.insert(billEntity)
        }
    }

    fun deleteBill(id: Int){
        viewModelScope.launch {
            billRepo.deleteAcc(id)
        }
    }
}