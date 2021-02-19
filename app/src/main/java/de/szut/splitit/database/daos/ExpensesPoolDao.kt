package de.szut.splitit.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.szut.splitit.database.entities.ExpensesPool
import de.szut.splitit.database.views.ExpensesPoolDetails

@Dao
interface ExpensesPoolDao {

    @Query("SELECT * FROM ExpensesPoolDetails WHERE (SELECT COUNT(*) FROM ExpensesPool) > 0")
    fun findAllExpensesPoolDetails(): Array<ExpensesPoolDetails>

    @Query("SELECT * FROM ExpensesPool WHERE expensesPoolId = :expensesPoolId")
    fun findById(expensesPoolId: Long): ExpensesPool?

    @Insert
    fun insert(expensesPool: ExpensesPool): Long

    @Update
    fun update(expensesPool: ExpensesPool)
}