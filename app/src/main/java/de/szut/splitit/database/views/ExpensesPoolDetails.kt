package de.szut.splitit.database.views

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT ep.expensesPoolId, ep.name, COUNT(u.userId) AS userCount, COUNT(ex.expenseId) AS expenseCount, SUM(ex.total) AS expenseTotal " +
            "FROM ExpensesPool AS ep " +
            "JOIN User u ON ep.expensesPoolId = u.expensesPoolId " +
            "JOIN Expense ex ON ex.expensesPoolId = ep.expensesPoolId"
)
data class ExpensesPoolDetails(
    val expensesPoolId: Long,
    val name: String?,
    val userCount: Int,
    val expenseCount: Int,
    val expenseTotal: Float
)
