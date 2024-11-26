package vn.edu.hust.studentman

data class Student(var name: String, var studentId: String) {
    override fun toString(): String {
        return "$name ($studentId)"
    }
}