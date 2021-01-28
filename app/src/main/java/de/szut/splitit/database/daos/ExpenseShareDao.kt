package de.szut.splitit.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import de.szut.splitit.database.entities.ExpenseShare

@Dao
interface ExpenseShareDao {
    @Insert
    fun insertAll(vararg expenseShares: ExpenseShare)

    @Delete
    fun deleteAll(vararg expenseShares: ExpenseShare)

}