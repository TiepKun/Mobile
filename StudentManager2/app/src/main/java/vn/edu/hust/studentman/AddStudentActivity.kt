package vn.edu.hust.studentman

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var studentIdEditText: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        nameEditText = findViewById(R.id.nameEditText)
        studentIdEditText = findViewById(R.id.studentIdEditText)
        addButton = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val studentId = studentIdEditText.text.toString()

            if (name.isNotEmpty() && studentId.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("student_name", name)
                resultIntent.putExtra("student_id", studentId)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}