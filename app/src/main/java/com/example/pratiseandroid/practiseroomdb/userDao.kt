package com.example.pratiseandroid.practiseroomdb
import androidx.room.Dao
import androidx.room.Insert


@Dao
interface UserDao {

    @Insert
    fun insertUser(userModel: UserModel)
}