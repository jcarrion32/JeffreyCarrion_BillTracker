package com.example.jeffreycarrion_billtracker.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "bill_table")
data class BillEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "bill") val bill: String?,
    @ColumnInfo(name = "due_date") val date: String?,
    @ColumnInfo(name = "amount") val amount: Double?
)
