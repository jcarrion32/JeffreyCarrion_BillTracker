package com.example.jeffreycarrion_billtracker.di

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.room.Room
import com.example.jeffreycarrion_billtracker.model.database.BillDatabase
import com.example.jeffreycarrion_billtracker.model.database.BillRepo
import com.example.jeffreycarrion_billtracker.viewmodel.BillViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        BillDatabase::class.java,
        "bill_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: BillDatabase) = db.billDao()

}
