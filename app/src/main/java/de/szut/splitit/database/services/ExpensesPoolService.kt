package de.szut.splitit.database.services

import android.content.Context
import de.szut.splitit.database.DatabaseHelper
import de.szut.splitit.database.SplitItAppDatabase
import de.szut.splitit.database.daos.ExpensesPoolDao
import de.szut.splitit.database.entities.ExpensesPool
import de.szut.splitit.database.views.ExpensesPoolDetails
import java.math.BigDecimal

class ExpensesPoolService(context: Context) {

    private val expensesPoolDao: ExpensesPoolDao =
        SplitItAppDatabase.getInstance(context).expensesPoolDao()

    fun save(expensesPool: ExpensesPool): Long? {
        val expensesPoolId: Long? = expensesPool
                .takeUnless(DatabaseHelper.ENTITY_EXISTS)
                ?.let(expensesPoolDao::insert)

        expensesPool
                .takeIf(DatabaseHelper.ENTITY_EXISTS)
                ?.apply(expensesPoolDao::update)

        return expensesPoolId
    }

    fun findAllExpensesPoolDetails(): ArrayList<ExpensesPoolDetails> {
        return expensesPoolDao.findAllExpensesPoolDetails().toCollection(arrayListOf())
    }

    fun findById(expensesPoolId: Long): ExpensesPool? {
        return expensesPoolDao.findById(expensesPoolId)
    }

}