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
data class User(
        @PrimaryKey(autoGenerate = true) var userId: Long = DatabaseHelper.ENTITY_DEFAULT_ID,
        var expensesPoolId: Long = DatabaseHelper.ENTITY_DEFAULT_ID,
        var name: String = ""
): BaseEntity {
    override fun getId(): Long {
        return userId
    }
}
