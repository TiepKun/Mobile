package com.example.roomstudentman

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomstudentman.adapter.StudentAdapter
import com.example.roomstudentman.database.StudentDatabase
import com.example.roomstudentman.entity.Student
import com.example.roomstudentman.viewmodel.StudentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var studentViewModel: StudentViewModel<Any?>
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val fabAddStudent: FloatingActionButton = findViewById(R.id.fabAddStudent)

        studentAdapter = StudentAdapter(this) { student ->
            val intent = Intent(this, StudentDetailActivity::class.java)
            intent.putExtra("STUDENT_ID", student.id)
            startActivity(intent)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = studentAdapter
        }

        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        studentViewModel.allStudents.observe(this) { students ->
            studentAdapter.submitList(students)
        }

        fabAddStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                studentViewModel.filterStudents(newText.orEmpty())
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_student -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteSelectedStudents() {
        val selectedIds = studentAdapter.getSelectedStudents().map { it.id }
        studentViewModel.deleteSelectedStudents(selectedIds)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_student -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_delete_selected -> {
                val selectedIds = studentAdapter.getSelectedStudents().map { it.id }
                if (selectedIds.isNotEmpty()) {
                    studentViewModel.deleteSelectedStudents(selectedIds)
                    Toast.makeText(this, "Đã xóa các sinh viên được chọn", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Không có sinh viên nào được chọn", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
