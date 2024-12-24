package com.example.roomstudentman.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomstudentman.database.StudentDatabase
import com.example.roomstudentman.entity.Student
import com.example.roomstudentman.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudentRepository
    var allStudents: LiveData<List<Student>>

    init {
        val studentDao = StudentDatabase.getInstance(application).studentDao()
        repository = StudentRepository(studentDao)
        allStudents = repository.allStudents
    }

    fun searchStudents(query: String): LiveData<List<Student>> {
        return repository.searchStudents(query)
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

    fun filter(keyword: String): LiveData<List<Student>> {
        val filteredStudents = MutableLiveData<List<Student>>()
        allStudents.observeForever { students ->
            val result = students.filter {
                it.name.contains(keyword, ignoreCase = true) ||
                        it.mssv.contains(keyword, ignoreCase = true)
            }
            filteredStudents.value = result
        }
        return filteredStudents
    }


}
