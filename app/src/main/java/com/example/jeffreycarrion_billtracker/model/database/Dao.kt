package com.example.jeffreycarrion_billtracker.model.database

import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM bill_table ORDER BY due_date ASC")
    fun getAllBills(): Flow<List<BillEntity>>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNew(billEntity: BillEntity)

    @Query("DELETE FROM bill_table WHERE id = :id")
    suspend fun delete(id: Int)

//    @Delete
//    fun delete(billEntity: BillEntity)
}