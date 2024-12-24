package com.example.roomstudentman.repository

import androidx.lifecycle.LiveData
import com.example.roomstudentman.database.StudentDao
import com.example.roomstudentman.entity.Student

class StudentRepository(private val studentDao: StudentDao) {
    val allStudents: LiveData<List<Student>> = studentDao.getAllStudents()

    fun searchStudents(query: String): LiveData<List<Student>> {
        return studentDao.searchStudents("%$query%")
    }

    suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun updateStudent(student: Student) {
        studentDao.updateStudent(student)
    }

    suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student)
    }

    suspend fun deleteSelectedStudents(ids: List<Int>) {
        studentDao.deleteSelectedStudents(ids)
    }
}