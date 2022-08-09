package com.example.jeffreycarrion_billtracker.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jeffreycarrion_billtracker.viewmodel.BillViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ViewModelFragment: Fragment() {

    protected val viewModel: BillViewModel by viewModels()
}


//    open val viewModel by activityViewModels<BillViewModel> {
//        ViewModelFactory(billRepo.repository)
//    }


//    private val applicationScope = CoroutineScope(SupervisorJob())
//    private val database by lazy {
//        BillDatabase.getDatabase(BillApp(), applicationScope)
//    }
//
//    private val repository by lazy {
//        BillRepo(database.billDao())
//    }