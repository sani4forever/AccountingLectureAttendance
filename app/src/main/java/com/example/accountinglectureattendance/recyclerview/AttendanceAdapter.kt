package com.example.accountinglectureattendance.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accountinglectureattendance.R
import com.example.accountinglectureattendance.attendance.Attendance

class AttendanceAdapter(private val attendanceList: List<Attendance>) :
    RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {

    class AttendanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectTextView: TextView = itemView.findViewById(R.id.textViewSubject)
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
        val missedHoursExcusedTextView: TextView = itemView.findViewById(R.id.textViewExcused)
        val missedHoursUnexcusedTextView: TextView = itemView.findViewById(R.id.textViewUnexcused)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.attendance_item, parent, false)
        return AttendanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val attendance = attendanceList[position]
        holder.subjectTextView.text = attendance.subject
        holder.dateTextView.text = attendance.date
        holder.missedHoursExcusedTextView.text = attendance.missedHoursExcused.toString()
        holder.missedHoursUnexcusedTextView.text = attendance.missedHoursUnexcused.toString()
    }

    override fun getItemCount(): Int = attendanceList.size
}

