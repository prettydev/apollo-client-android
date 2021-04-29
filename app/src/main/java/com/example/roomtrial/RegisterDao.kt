package com.example.roomtrial

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface RegisterDao {
    @Query ("SELECT * FROM UserEntity")
    fun getAllUser(): List<UserEntity>

    @Insert (onConflict = REPLACE)
    fun registerUser(user: UserEntity) : Long

    @Update
    fun updateUser(user : UserEntity) : Int

    @Delete
    fun deleteUser(user: UserEntity) : Int

    @Query("SELECT * FROM UserEntity WHERE nama=:nama AND password=:password")
    fun getUser(nama: String, password :String) : UserEntity
}