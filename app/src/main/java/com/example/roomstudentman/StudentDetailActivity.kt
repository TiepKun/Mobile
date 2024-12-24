package com.example.roomstudentman
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomstudentman.entity.Student
import com.example.roomstudentman.viewmodel.StudentViewModel

class StudentDetailActivity : AppCompatActivity() {

    private lateinit var studentViewModel: StudentViewModel
    private var studentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        val etMSSV: EditText = findViewById(R.id.etMSSV)
        val etName: EditText = findViewById(R.id.etName)
        val etDOB: EditText = findViewById(R.id.etDOB)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val btnUpdate: Button = findViewById(R.id.btnUpdate)
        val btnDelete: Button = findViewById(R.id.btnDelete)

        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        studentId = intent.getIntExtra("STUDENT_ID", 0)

        // Lấy thông tin chi tiết của sinh viên
        studentViewModel.allStudents.observe(this) { students ->
            val student = students.find { it.id == studentId }
            student?.let {
                etMSSV.setText(it.mssv)
                etName.setText(it.name)
                etDOB.setText(it.dob)
                etEmail.setText(it.email)
            }
        }

        // Cập nhật sinh viên
        btnUpdate.setOnClickListener {
            val updatedStudent = Student(
                id = studentId,
                mssv = etMSSV.text.toString(),
                name = etName.text.toString(),
                dob = etDOB.text.toString(),
                email = etEmail.text.toString()
            )
            studentViewModel.updateStudent(updatedStudent)
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Xóa sinh viên
        btnDelete.setOnClickListener {
            studentViewModel.deleteStudent(Student(id = studentId, mssv = "", name = "", dob = "", email = ""))
            Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
