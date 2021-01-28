package de.szut.splitit.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import de.szut.splitit.database.entities.Expense

@Dao
interface ExpenseDao {
    @Insert
    fun insertAll(vararg expenses: Expense)

    @Delete
    fun deleteAll(vararg expenses: Expense)

}