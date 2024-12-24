package com.example.roomstudentman.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomstudentman.database.StudentDatabase
import com.example.roomstudentman.entity.Student
import com.example.roomstudentman.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudentRepository
    val allStudents: LiveData<List<Student>>
    val filteredStudents = MediatorLiveData<List<Student>>()

    init {
        val studentDao = StudentDatabase.getInstance(application).studentDao()
        repository = StudentRepository(studentDao)
        allStudents = repository.allStudents
    }

    fun filter(keyword: String) {
        filteredStudents.addSource(allStudents) { students ->
            filteredStudents.value = students.filter { student ->
                student.name.contains(keyword, ignoreCase = true) ||
                        student.mssv.contains(keyword, ignoreCase = true)
            }
        }
    }

    fun insertStudent(student: Student) {
        viewModelScope.launch {
            repository.insertStudent(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            repository.updateStudent(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            repository.deleteStudent(student)
        }
    }

    fun deleteSelectedStudents(ids: List<Int>) {
        viewModelScope.launch {
            repository.deleteSelectedStudents(ids)
        }
    }
}
