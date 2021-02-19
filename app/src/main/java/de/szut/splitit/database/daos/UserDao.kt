package de.szut.splitit.database.daos

import androidx.room.*
import de.szut.splitit.database.entities.User

@Dao
interface UserDao {
    @Insert
    fun insertAll(users: List<User>)

    @Query("SELECT * FROM User WHERE expensesPoolId = :expensesPoolId")
    fun findByExpensesPoolId(expensesPoolId: Long): Array<User>

    @Update
    fun updateAll(users: List<User>)

    @Delete
    fun deleteAll(vararg users: User)

    fun delete(user: User) {
        deleteAll(user)
    }

}