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
//    private var appContext: Context? = null
//
//    fun setAppContext(context: Context): Context {
//        appContext = context
//        return appContext
//    }
//
//    private val applicationScope = CoroutineScope(SupervisorJob())
//
//    private val database by lazy {
//        BillDatabase.getDatabase(setAppContext(appContext), applicationScope)
//    }
//
//    val repository by lazy {
//        BillRepo(database.billDao())
//    }
//
//
//    fun providerViewModel(storeOwner: ViewModelStoreOwner): BillViewModel {
//        return ViewModelProvider(storeOwner, object: ViewModelProvider.Factory{
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return return modelClass.getConstructor(BillRepo::class.java).newInstance(repository)
//            }
//        })[BillViewModel::class.java]
//    }

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

//class ViewModelFactory(
//    private val repo: BillRepo
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return modelClass.getConstructor(BillRepo::class.java).newInstance(repo)
//    }
//}