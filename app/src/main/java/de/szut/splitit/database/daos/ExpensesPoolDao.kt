package de.szut.splitit.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.szut.splitit.database.entities.ExpensesPool
import de.szut.splitit.database.views.ExpensesPoolDetails

@Dao
interface ExpensesPoolDao {

    @Query("SELECT * FROM ExpensesPoolDetails WHERE (SELECT COUNT(*) FROM ExpensesPool) > 0")
    fun findAllExpensesPoolDetails(): List<ExpensesPoolDetails>

    @Insert
    fun insertAll(expensesPool: List<ExpensesPool>)

    @Query("DELETE FROM ExpensesPool WHERE expensesPoolId = :id")
    fun deleteById(id: Long)


}