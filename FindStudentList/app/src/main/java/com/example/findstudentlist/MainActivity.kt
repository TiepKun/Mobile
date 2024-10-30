package com.example.findstudentlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var studentListView: ListView
    private lateinit var searchEditText: EditText
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: MutableList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentList = mutableListOf(
            Student("Nguyen Van A", "100001"),
            Student("Tran Thi B", "100002"),
            Student("Le Van C", "100003"),
            Student("Pham Thi D", "100004"),
            Student("Vo Thi E", "100005"),
            Student("Bui Van F", "100006"),
            Student("Nguyen Thi G", "100007"),
            Student("Do Van H", "100008"),
            Student("Phan Thi I", "100009"),
            Student("Hoang Van J", "100010"),
            Student("Nguyen Thi K", "100011"),
            Student("Tran Van L", "100012"),
            Student("Le Thi M", "100013"),
            Student("Pham Van N", "100014"),
            Student("Vo Thi O", "100015"),
            Student("Bui Van P", "100016"),
            Student("Nguyen Thi Q", "100017"),
            Student("Do Van R", "100018"),
            Student("Phan Thi S", "100019"),
            Student("Hoang Van T", "100020"),
            Student("Nguyen Van U", "100021"),
            Student("Tran Thi V", "100022"),
            Student("Le Van W", "100023"),
            Student("Pham Thi X", "100024"),
            Student("Vo Van Y", "100025"),
            Student("Bui Thi Z", "100026"),
            Student("Nguyen Van AA", "100027"),
            Student("Tran Thi AB", "100028"),
            Student("Le Van AC", "100029"),
            Student("Pham Thi AD", "100030"),
            Student("Vo Van AE", "100031"),
            Student("Bui Thi AF", "100032"),
            Student("Nguyen Thi AG", "100033"),
            Student("Do Van AH", "100034"),
            Student("Phan Thi AI", "100035"),
            Student("Hoang Van AJ", "100036"),
            Student("Nguyen Van AK", "100037"),
            Student("Tran Thi AL", "100038"),
            Student("Le Van AM", "100039"),
            Student("Pham Thi AN", "100040"),
            Student("Vo Van AO", "100041"),
            Student("Bui Thi AP", "100042"),
            Student("Nguyen Thi AQ", "100043"),
            Student("Do Van AR", "100044"),
            Student("Phan Thi AS", "100045"),
            Student("Hoang Van AT", "100046"),
            Student("Nguyen Van AU", "100047"),
            Student("Tran Thi AV", "100048"),
            Student("Le Van AW", "100049"),
            Student("Pham Thi AX", "100050"),
            Student("Vo Van AY", "100051"),
            Student("Bui Thi AZ", "100052"),

        )

        studentListView = findViewById(R.id.studentListView)
        studentAdapter = StudentAdapter(this, studentList)
        studentListView.adapter = studentAdapter

        searchEditText = findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun filterList(query: String) {
        val filteredList = if (query.length > 2) {
            studentList.filter {
                it.name.contains(query, ignoreCase = true) || it.mssv.contains(query)
            }
        } else {
            studentList
        }

        studentAdapter = StudentAdapter(this, filteredList)
        studentListView.adapter = studentAdapter
    }
}
