package de.szut.splitit.database.services

import android.content.Context
import de.szut.splitit.database.DatabaseHelper
import de.szut.splitit.database.SplitItAppDatabase
import de.szut.splitit.database.daos.UserDao
import de.szut.splitit.database.entities.User


class UserService(context: Context) {

    private val userDao: UserDao =
        SplitItAppDatabase.getInstance(context).userDao()

    fun save(users: List<User>) {
        val newUsers: List<User> = users.filter(DatabaseHelper.ENTITY_EXISTS)
        val existingUsers: List<User> = users.filterNot(DatabaseHelper.ENTITY_EXISTS)
        userDao.insertAll(newUsers)
        userDao.updateAll(existingUsers)
    }

    fun findByExpensesPoolId(expensesPoolId: Long): ArrayList<User> {
        return userDao.findByExpensesPoolId(expensesPoolId).toCollection(arrayListOf())
    }

    fun delete(user: User) {
        user.takeIf(DatabaseHelper.ENTITY_EXISTS)
            ?.apply(userDao::delete)
    }

}