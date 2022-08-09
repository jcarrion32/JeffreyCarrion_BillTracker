package com.example.jeffreycarrion_billtracker

import android.app.Application
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.example.jeffreycarrion_billtracker.di.DI
//import com.example.jeffreycarrion_billtracker.di.DI.repository
import com.example.jeffreycarrion_billtracker.model.database.BillDatabase
import com.example.jeffreycarrion_billtracker.model.database.BillRepo
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class BillApp: Application() {
//    private val applicationScope = CoroutineScope(SupervisorJob())
//
//    private val database by lazy {
//        BillDatabase.getDatabase(this, applicationScope)
//    }
//
//    val repository by lazy {
//        BillRepo(database.billDao())
//    }

    override fun onCreate() {
        super.onCreate()
        var database = DI.provideYourDatabase(this)
        DI.provideDao(database)

//        newApp(applicationScope, database, repository)
    }

//    companion object {
//        private var instance: BillApp? = null
//
//        fun newApp(scope: CoroutineScope, database: BillDatabase, repo: BillRepo): BillApp? {
//            if (instance == null) instance = BillApp()
//
//            return instance
//        }
//    }
}