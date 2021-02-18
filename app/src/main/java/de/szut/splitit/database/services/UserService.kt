package de.szut.splitit.database.services

import android.content.Context
import de.szut.splitit.database.SplitItAppDatabase
import de.szut.splitit.database.daos.UserDao
import de.szut.splitit.database.entities.User


class UserService(context: Context) {

    private val userDao: UserDao =
        SplitItAppDatabase.getInstance(context.applicationContext).userDao()

    fun save(users: List<User>) {
        userDao.insertAll(users)
    }

}