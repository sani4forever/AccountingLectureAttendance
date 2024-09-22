package com.example.accountinglectureattendance.attendance

data class Attendance(
    val subject: String,
    val date: String,
    val missedHoursExcused: Int,
    val missedHoursUnexcused: Int,
)