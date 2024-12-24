package com.example.roomstudentman.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomstudentman.R
import com.example.roomstudentman.entity.Student

class StudentAdapter(
    private val context: Context,
    private val onItemClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private val students = mutableListOf<Student>()
    private val selectedStudents = mutableListOf<Student>()

    fun submitList(studentList: List<Student>) {
        students.clear()
        students.addAll(studentList)
        notifyDataSetChanged()
    }

    fun getSelectedStudents(): List<Student> {
        return selectedStudents
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
        holder.itemView.setOnClickListener { onItemClick(student) }
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedStudents.add(student)
            } else {
                selectedStudents.remove(student)
            }
        }
    }

    override fun getItemCount(): Int = students.size

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvMSSV: TextView = view.findViewById(R.id.tvMSSV)
        private val tvHoTen: TextView = view.findViewById(R.id.tvHoTen)
        val checkBox: CheckBox = view.findViewById(R.id.checkbox)

        fun bind(student: Student) {
            tvMSSV.text = student.mssv
            tvHoTen.text = student.name
        }
    }
}
