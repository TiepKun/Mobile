package vn.edu.hust.studentman

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var studentIdEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        nameEditText = findViewById(R.id.nameEditText)
        studentIdEditText = findViewById(R.id.studentIdEditText)
        saveButton = findViewById(R.id.saveButton)

        // Nhận dữ liệu từ MainActivity
        val name = intent.getStringExtra("student_name")
        val studentId = intent.getStringExtra("student_id")
        val position = intent.getIntExtra("position", -1)

        nameEditText.setText(name)
        studentIdEditText.setText(studentId)

        saveButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedStudentId = studentIdEditText.text.toString()

            if (updatedName.isNotEmpty() && updatedStudentId.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("student_name", updatedName)
                resultIntent.putExtra("student_id", updatedStudentId)
                resultIntent.putExtra("position", position)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}