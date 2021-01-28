package de.szut.splitit.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = Expense::class,
        parentColumns = arrayOf("expenseId"),
        childColumns = arrayOf("expenseId"),
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )
])
data class ExpenseShare(
    @PrimaryKey(autoGenerate = true) val expenseShareId: Long,
    val expenseId: Long,
    val userId: Long,
    val valueInPercentage: Boolean,
    val value: Float
)
