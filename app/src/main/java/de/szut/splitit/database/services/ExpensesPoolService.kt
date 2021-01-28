package de.szut.splitit.database.services

import android.content.Context
import de.szut.splitit.database.SplitItAppDatabase
import de.szut.splitit.database.daos.ExpensesPoolDao

class ExpensesPoolService(context: Context) {

    private val expensesPoolDao: ExpensesPoolDao =
        SplitItAppDatabase.getInstance(context.applicationContext).expensesPoolDao()

}