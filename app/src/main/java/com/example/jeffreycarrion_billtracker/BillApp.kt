package com.example.jeffreycarrion_billtracker

import android.app.Application
import com.example.jeffreycarrion_billtracker.di.DI
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BillApp: Application() {
    override fun onCreate() {
        super.onCreate()
        val database = DI.provideYourDatabase(this)
        DI.provideDao(database)

       }
}