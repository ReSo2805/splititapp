package de.szut.splitit.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.szut.splitit.database.DatabaseHelper

@Entity
data class ExpensesPool(
        @PrimaryKey(autoGenerate = true) var expensesPoolId: Long = DatabaseHelper.ENTITY_DEFAULT_ID,
        var name: String = ""
): BaseEntity {
    override fun getId(): Long {
        return expensesPoolId
    }
}
