package de.szut.splitit.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.szut.splitit.database.DatabaseHelper

@Entity(foreignKeys = [
    ForeignKey(
        entity = ExpensesPool::class,
        parentColumns = arrayOf("expensesPoolId"),
        childColumns = arrayOf("expensesPoolId"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Expense(
        @PrimaryKey(autoGenerate = true) var expenseId: Long = DatabaseHelper.ENTITY_DEFAULT_ID,
        var expensesPoolId: Long = DatabaseHelper.ENTITY_DEFAULT_ID,
        var total: Float,
        var name: String,

        ): BaseEntity {
    override fun getId(): Long {
        return expenseId
    }
}
