package de.szut.splitit.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpensesPool(
    @PrimaryKey(autoGenerate = true) val expensesPoolId: Long,
    val name: String
)