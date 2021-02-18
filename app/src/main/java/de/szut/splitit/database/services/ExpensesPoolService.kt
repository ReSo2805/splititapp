package de.szut.splitit.database.services

import android.content.Context
import de.szut.splitit.database.SplitItAppDatabase
import de.szut.splitit.database.daos.ExpensesPoolDao
import de.szut.splitit.database.entities.ExpensesPool

class ExpensesPoolService(context: Context) {

    private val expensesPoolDao: ExpensesPoolDao =
        SplitItAppDatabase.getInstance(context).expensesPoolDao()

    fun save(expensesPool: ExpensesPool) {
        expensesPoolDao.insertAll(listOf(expensesPool))
    }

}