package com.example.jeffreycarrion_billtracker.model.database

import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BillRepo
    @Inject constructor
    (private val billDao: Dao?): ViewModelProvider.Factory {

    val allBill: Flow<List<BillEntity>>? = billDao?.getAllBills()

    //take suspend out cus DAO glitch
    suspend fun insert(billEntity: BillEntity) {
        billDao?.insertNew(billEntity)
    }

    suspend fun deleteAcc(id: Int){
        billDao?.delete(id)
    }


//    fun providerViewModel(storeOwner: ViewModelStoreOwner): BillViewModel{
//        return ViewModelProvider(storeOwner, object: ViewModelProvider.Factory{
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return BillViewModel(BillRepo(billDao)) as T
//            }
//        })[BillViewModel::class.java]
//    }
}