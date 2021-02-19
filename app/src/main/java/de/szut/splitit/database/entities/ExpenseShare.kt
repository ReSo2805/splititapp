package de.szut.splitit.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.szut.splitit.database.DatabaseHelper

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
        @PrimaryKey(autoGenerate = true) var expenseShareId: Long = DatabaseHelper.ENTITY_DEFAULT_ID,
        var expenseId: Long = DatabaseHelper.ENTITY_DEFAULT_ID,
        var userId: Long = DatabaseHelper.ENTITY_DEFAULT_ID,
        var valueInPercentage: Boolean,
        var value: Float
): BaseEntity {
    override fun getId(): Long {
        return expenseShareId
    }

}
