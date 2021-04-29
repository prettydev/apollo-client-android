package com.example.roomtrial

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDb : RoomDatabase() {
    abstract fun registerDao(): RegisterDao


    companion object {
        private var INSTANCE: UserDb? = null

        fun getInstance(context: Context): UserDb? {
            if (INSTANCE == null) {
                synchronized(UserDb::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDb::class.java, "UserDb"
                    ).build()

                }

            }

            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}
