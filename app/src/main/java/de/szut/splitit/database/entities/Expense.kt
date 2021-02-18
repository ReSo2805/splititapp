package de.szut.splitit.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = ExpensesPool::class,
        parentColumns = arrayOf("expensesPoolId"),
        childColumns = arrayOf("expensesPoolId"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Expense(
    @PrimaryKey(autoGenerate = true) var expenseId: Long,
    var expensesPoolId: Long,
    var total: Float,
    var name: String
)
