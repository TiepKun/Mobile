package com.example.roomstudentman

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomstudentman.entity.Student
import com.example.roomstudentman.viewmodel.StudentViewModel
import com.example.roomstudentman.R


class AddStudentActivity : AppCompatActivity() {

    private lateinit var studentViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val etMSSV: EditText = findViewById(R.id.etMSSV)
        val etName: EditText = findViewById(R.id.etName)
        val etDOB: EditText = findViewById(R.id.etDOB)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val btnSave: Button = findViewById(R.id.btnSave)

        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        btnSave.setOnClickListener {
            val student = Student(
                mssv = etMSSV.text.toString(),
                name = etName.text.toString(),
                dob = etDOB.text.toString(),
                email = etEmail.text.toString()
            )
            studentViewModel.insertStudent(student)
            finish()
        }
    }
}