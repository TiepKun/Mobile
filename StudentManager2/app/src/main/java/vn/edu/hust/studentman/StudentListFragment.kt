package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import vn.edu.hust.studentman.databinding.FragmentStudentListBinding

class StudentListFragment<FragmentStudentListBinding> : Fragment() {

    private lateinit var binding: FragmentStudentListBinding
    private lateinit var students: ArrayList<Student>
    private lateinit var adapter: ArrayAdapter<Student>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentListBinding.inflate(inflater, container, false)

        students = arrayListOf(
            Student("Nguyen Van A", "12345"),
            Student("Le Van B", "67890")
        )

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, students)
        binding.studentListView.adapter = adapter

        // Khi chọn một item trong danh sách
        binding.studentListView.setOnItemClickListener { _, _, position, _ ->
            val action = StudentListFragmentDirections.actionToEditStudentFragment(
                studentName = students[position].name,
                studentId = students[position].studentId,
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
}
