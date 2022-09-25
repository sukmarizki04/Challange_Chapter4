package com.example.appchapterfour.center.asist

import java.text.SimpleDateFormat
import java.util.*


object DataAsist {
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}