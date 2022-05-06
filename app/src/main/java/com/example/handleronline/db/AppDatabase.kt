package com.example.handleronline.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.handleronline.dao.UserDao
import com.example.handleronline.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{
        private var instanse : AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : AppDatabase{
            if(instanse == null){
                instanse = Room.databaseBuilder(context, AppDatabase::class.java, "user.db")
                    //it deletes all info if the app's version is changed
                    .fallbackToDestructiveMigration()
                    //It create DB in MainThread
                    .allowMainThreadQueries()
                    .build()
            }
            return instanse!!
        }
    }
}