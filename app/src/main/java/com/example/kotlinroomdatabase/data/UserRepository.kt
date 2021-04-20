package com.example.kotlinroomdatabase.data

import androidx.lifecycle.LiveData

// User Repository abstracts access to multiple data sources. However this is not the part of the Architecture Component libraries.

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}