package de.szut.splitit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.szut.splitit.database.daos.ExpenseDao
import de.szut.splitit.database.daos.ExpenseShareDao
import de.szut.splitit.database.daos.ExpensesPoolDao
import de.szut.splitit.database.daos.UserDao
import de.szut.splitit.database.entities.Expense
import de.szut.splitit.database.entities.ExpenseShare
import de.szut.splitit.database.entities.ExpensesPool
import de.szut.splitit.database.entities.User
import de.szut.splitit.database.views.ExpensesPoolDetails

@Database(
    entities = [ExpensesPool::class, Expense::class, ExpenseShare::class, User::class],
    views = [ExpensesPoolDetails::class],
    version = 1
)
abstract class SplitItAppDatabase: RoomDatabase() {

    companion object {
        private var DATABASE_NAME: String = "SplitItApp.db"
        private var INSTANCE: SplitItAppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SplitItAppDatabase {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
            return INSTANCE!!;
        }

        private fun createDatabase(context: Context): SplitItAppDatabase {
           return Room.databaseBuilder(context, SplitItAppDatabase::class.java, DATABASE_NAME)
               .fallbackToDestructiveMigration()
               .allowMainThreadQueries() //TODO should be removed
               .build()
        }
    }

    abstract fun userDao(): UserDao
    abstract fun expensesPoolDao(): ExpensesPoolDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun expenseShareDao(): ExpenseShareDao

}
