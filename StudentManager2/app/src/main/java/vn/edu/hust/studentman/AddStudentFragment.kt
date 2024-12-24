package vn.edu.hust.studentman
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import vn.edu.hust.studentman.R
import androidx.navigation.fragment.findNavController

class AddStudentFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var studentIdEditText: EditText
    private lateinit var addButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)
        nameEditText = view.findViewById(R.id.nameEditText)
        studentIdEditText = view.findViewById(R.id.studentIdEditText)
        addButton = view.findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val studentId = studentIdEditText.text.toString()

            if (name.isNotEmpty() && studentId.isNotEmpty()) {
                // Gửi dữ liệu về StudentListFragment
                val action = AddStudentFragmentDirections.actionAddStudentFragmentToStudentListFragment(
                    studentName = name, studentId = studentId
                )
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
