package com.example.accountinglectureattendance.fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.accountinglectureattendance.attendance.Attendance
import com.example.accountinglectureattendance.attendance.TableRowItem
import com.example.accountinglectureattendance.databinding.FragmentAttendanceBinding
import com.example.accountinglectureattendance.recyclerview.TableAdapter
import kotlin.random.Random


class AttendanceFragment : Fragment() {
    private var _binding: FragmentAttendanceBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        val items = arrayOf("10701123", "10701223", "10701323")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)
        binding.groupSpinner.adapter = adapter
        binding.calendarButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    Toast.makeText(requireContext(), "Вы выбрали дату: $selectedDate", Toast.LENGTH_SHORT).show()
                },
                year, month, day
            )

            datePickerDialog.show()
        }
        fun generateRandomData(): List<TableRowItem> {
            val studentNames = listOf("Гошко А.И.", "Иванов И.И.", "Петров П.П.", "Сидоров С.С.")
            val subjects = listOf("Физическая культура", "Численные методы", "Теория информации", "Алгоритмы и структуры данных", "Теория вероятностей")
            val dates = listOf("22 Фев", "02 Сен", "03 Сен", "04 Сен", "05 Сен")

            val rows = mutableListOf<TableRowItem>()

            for (name in studentNames) {
                val attendanceList = mutableListOf<Attendance>()

                for (i in subjects.indices) {
                    val excused = Random.nextInt(0, 3)
                    val unexcused = Random.nextInt(0, 3)

                    attendanceList.add(
                        Attendance(
                            subject = subjects[i],
                            date = dates[i],
                            missedHoursExcused = excused,
                            missedHoursUnexcused = unexcused
                        )
                    )
                }

                rows.add(TableRowItem(name = name, attendance = attendanceList))
            }

            return rows
        }

        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.adapter = TableAdapter(generateRandomData())


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            AttendanceFragment().apply { }
    }
}