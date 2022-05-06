package com.example.handleronline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.handleronline.entity.User

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User)

    @Query("select * from user_table")
    fun getAllUsers() : List<User>
}