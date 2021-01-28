package de.szut.splitit.database.services

import android.content.Context
import de.szut.splitit.database.SplitItAppDatabase
import de.szut.splitit.database.daos.ExpenseDao

class ExpenseService(context: Context) {

    private val expenseDao: ExpenseDao =
        SplitItAppDatabase.getInstance(context.applicationContext).expenseDao()

}