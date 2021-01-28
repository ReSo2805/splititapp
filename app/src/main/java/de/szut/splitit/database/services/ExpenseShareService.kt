package de.szut.splitit.database.services

import android.content.Context
import de.szut.splitit.database.SplitItAppDatabase
import de.szut.splitit.database.daos.ExpenseShareDao

class ExpenseShareService(context: Context) {

    private val expenseShare: ExpenseShareDao =
        SplitItAppDatabase.getInstance(context.applicationContext).expenseShareDao()

}