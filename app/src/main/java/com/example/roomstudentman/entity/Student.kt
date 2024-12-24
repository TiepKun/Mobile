package com.example.roomstudentman.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mssv: String,
    val name: String,
    val dob: String,
    val email: String
)