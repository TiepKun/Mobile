package com.example.findstudentlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

data class Student(val name: String, val mssv: String)


class StudentAdapter(context: Context, private val studentList: List<Student>) :
    ArrayAdapter<Student>(context, 0, studentList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_list_item_2, parent, false
        )

        val student = studentList[position]
        val nameTextView = view.findViewById<TextView>(android.R.id.text1)
        val mssvTextView = view.findViewById<TextView>(android.R.id.text2)

        nameTextView.text = student.name
        mssvTextView.text = "MSSV: ${student.mssv}"

        return view
    }
}
