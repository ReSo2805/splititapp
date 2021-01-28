package de.szut.splitit.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import de.szut.splitit.database.entities.User

@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun deleteAll(vararg users: User)

}