package com.example.handleronline.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
    var username : String? = null
    var password : String? = null
}