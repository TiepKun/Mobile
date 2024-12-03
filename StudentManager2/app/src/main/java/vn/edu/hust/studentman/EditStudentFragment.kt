package vn.edu.hust.studentman
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import vn.edu.hust.studentman.R

class EditStudentFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var studentIdEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)
        nameEditText = view.findViewById(R.id.nameEditText)
        studentIdEditText = view.findViewById(R.id.studentIdEditText)
        saveButton = view.findViewById(R.id.saveButton)

        // Nhận dữ liệu từ StudentListFragment
        val args = EditStudentFragmentArgs.fromBundle(requireArguments())
        nameEditText.setText(args.studentName)
        studentIdEditText.setText(args.studentId)

        saveButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedStudentId = studentIdEditText.text.toString()

            if (updatedName.isNotEmpty() && updatedStudentId.isNotEmpty()) {
                // Gửi dữ liệu cập nhật về StudentListFragment
                val action = EditStudentFragmentDirections.actionEditStudentFragmentToStudentListFragment(
                    studentName = updatedName,
                    studentId = updatedStudentId,
                    position = args.position
                )
                findNavController().navigate(action)
            }
        }

        return view
    }
}
