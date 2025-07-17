package com.example.pratiseandroid.practiseroomdb
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Insert
    fun insertUser(userModel: UserModel)

    @Query("SELECT*FROM UserModel")
    fun getUserData(): List<UserModel>
}