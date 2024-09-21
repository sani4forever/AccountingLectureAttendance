package com.example.accountinglectureattendance.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accountinglectureattendance.R
import com.example.accountinglectureattendance.attendance.TableRowItem

class TableAdapter(private val rows: List<TableRowItem>) :
    RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        val recyclerViewAttendance: RecyclerView = itemView.findViewById(R.id.recyclerViewAttendance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.table_row_item, parent, false)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val rowItem = rows[position]
        holder.nameTextView.text = rowItem.name

        val attendanceAdapter = AttendanceAdapter(rowItem.attendance)
        holder.recyclerViewAttendance.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)

        holder.recyclerViewAttendance.adapter = attendanceAdapter
    }

    override fun getItemCount(): Int = rows.size
}

