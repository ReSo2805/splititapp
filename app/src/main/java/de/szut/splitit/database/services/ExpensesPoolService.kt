package de.szut.splitit.database.services

import android.content.Context
import androidx.lifecycle.LiveData
import de.szut.splitit.database.DatabaseHelper
import de.szut.splitit.database.SplitItAppDatabase
import de.szut.splitit.database.daos.ExpensesPoolDao
import de.szut.splitit.database.entities.ExpensesPool
import de.szut.splitit.database.views.ExpensesPoolDetails

class ExpensesPoolService(context: Context) {

    private val expensesPoolDao: ExpensesPoolDao =
        SplitItAppDatabase.getInstance(context).expensesPoolDao()

    fun save(expensesPool: ExpensesPool): Long? {
        expensesPool
                .takeIf(DatabaseHelper.ENTITY_EXISTS)
                ?.apply(expensesPoolDao::update)

        return expensesPool
                .takeUnless(DatabaseHelper.ENTITY_EXISTS)
                ?.let(expensesPoolDao::insert)
    }

    fun findAllExpensesPoolDetails(): LiveData<List<ExpensesPoolDetails>> {
        return expensesPoolDao.findAllExpensesPoolDetails()
    }

    fun findById(expensesPoolId: Long): ExpensesPool {
        return expensesPoolDao.findById(expensesPoolId)
    }

    fun deleteById(expensesPoolId: Long) {
        expensesPoolDao.deleteById(expensesPoolId)
    }

}