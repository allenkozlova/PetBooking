package com.example.petbooking.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getDateRange (dateFrom: String, dateTo: String): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("ru"))
        var resultText = ""
        try {
            val newDateFrom = formatter.parse(dateFrom)
            val newDateTo = formatter.parse(dateTo)
            val spf = SimpleDateFormat("dd.MM.yyyy", Locale("ru"))
            val formattedDateFrom = spf.format(newDateFrom)
            val formattedDateTo = spf.format(newDateTo)
            resultText = "$dateFrom - $dateTo"
        } catch (e: ParseException) { }
        return resultText
    }
}