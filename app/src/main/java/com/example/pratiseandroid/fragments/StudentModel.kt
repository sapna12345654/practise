package com.example.pratiseandroid.fragments

data class StudentModel(
    var name: String? = null,
    var rollNo: String? = null,
    var contactNo: String? = null

){
    override fun toString(): String {
        return name.toString()
    }
}