package com.example.roomstudentman.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomstudentman.entity.Student

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM students WHERE id IN (:ids)")
    suspend fun deleteSelectedStudents(ids: List<Int>)

    @Query("SELECT * FROM students WHERE mssv LIKE :query OR name LIKE :query ORDER BY name ASC")
    fun searchStudents(query: String): LiveData<List<Student>>

    @Query("SELECT * FROM students ORDER BY name ASC")
    fun getAllStudents(): LiveData<List<Student>>
}