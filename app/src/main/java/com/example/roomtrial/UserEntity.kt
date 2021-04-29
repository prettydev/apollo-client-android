package com.example.roomtrial

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "nama") var nama: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "umur") var umur: Int,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name= "image") var image : String
)