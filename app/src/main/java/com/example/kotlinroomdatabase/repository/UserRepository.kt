package com.example.kotlinroomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.kotlinroomdatabase.data.UserDao
import com.example.kotlinroomdatabase.model.User

// User Repository abstracts access to multiple data sources. However this is not the part of the Architecture Component libraries.

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
}