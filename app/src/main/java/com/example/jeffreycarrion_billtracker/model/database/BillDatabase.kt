package com.example.jeffreycarrion_billtracker.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [BillEntity::class], version = 1, exportSchema = false)
abstract class BillDatabase: RoomDatabase() {

    abstract fun billDao(): Dao?

//    companion object{
//        @Volatile
//        private var INSTANCE: BillDatabase? = null
//
//        fun getDatabase(
//            context: Context,
//            scope: CoroutineScope
//        ): BillDatabase {
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context,
//                    BillDatabase::class.java,
//                    "bill_database"
//                )
//                    .fallbackToDestructiveMigration()
//                    .addCallback(BillDatabaseCallback(scope))
//                    .build()
//
//                INSTANCE = instance
//
//                instance
//            }
//        }
//
//        private class BillDatabaseCallback(
//            private val scope: CoroutineScope
//        ): RoomDatabase.Callback(){
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                INSTANCE?.let { database ->
//                    scope.launch(Dispatchers.IO) {
//                        database.billDao()
//                    }
//                }
//            }
//        }
//    }
}


