package de.szut.splitit.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import de.szut.splitit.database.entities.ExpensesPool
import de.szut.splitit.database.views.ExpensesPoolDetails

@Dao
interface ExpensesPoolDao {

    @Query("SELECT * FROM ExpensesPoolDetails WHERE (SELECT COUNT(*) FROM ExpensesPool) > 0")
    fun findAllExpensesPoolDetails(): LiveData<List<ExpensesPoolDetails>>

    @Query("SELECT * FROM ExpensesPool WHERE expensesPoolId = :expensesPoolId")
    fun findById(expensesPoolId: Long): ExpensesPool

    @Insert
    fun insert(expensesPool: ExpensesPool): Long

    @Update
    fun update(expensesPool: ExpensesPool)

    @Query("DELETE FROM ExpensesPool WHERE expensesPoolId = :expensesPoolId")
    fun deleteById(expensesPoolId: Long)
}