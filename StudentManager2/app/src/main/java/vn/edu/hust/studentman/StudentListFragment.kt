package vn.edu.hust.studentman

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import vn.edu.hust.studentman.databinding.FragmentStudentListBinding

class StudentListFragment : Fragment() {

    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var students: ArrayList<Student>
    private lateinit var adapter: ArrayAdapter<Student>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using View Binding
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)

        // Initialize sample student data
        students = arrayListOf(
            Student("Nguyen Van A", "12345"),
            Student("Le Van B", "67890")
        )

        // Initialize and set adapter
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, students)
        binding.studentListView.adapter = adapter

        // Handle item click in the list
        binding.studentListView.setOnItemClickListener { _, _, position, _ ->
            val selectedStudent = students[position]
            val action = StudentListFragmentDirections.actionToEditStudentFragment(
                studentName = selectedStudent.name,
                studentId = selectedStudent.studentId,
                position = position
            )
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_new -> {
                findNavController().navigate(R.id.action_to_addStudentFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
