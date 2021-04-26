package com.example.kotlinroomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlinroomdatabase.model.User

// UserDao contains the methods used for accessing the database, including queries.
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // <- Annotate the 'addUser' function below. Set the onConflict strategy to IGNORE so if exactly the same user exists, it will just ignore it.
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * from user_table ORDER BY id ASC") // <- Add a query to fetch all users (in user_table) in ascending order by their IDs.
    fun readAllData(): LiveData<List<User>> // <- This means function return type is List. Specifically, a List of Users.
}