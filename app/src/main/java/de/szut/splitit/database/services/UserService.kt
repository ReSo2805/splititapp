package de.szut.splitit.database.services

import android.content.Context
import de.szut.splitit.database.SplitItAppDatabase
import de.szut.splitit.database.daos.UserDao


class UserService(context: Context) {

    private val userDao: UserDao =
        SplitItAppDatabase.getInstance(context.applicationContext).userDao()

}