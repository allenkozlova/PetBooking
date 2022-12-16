package com.example.petbooking.utils

import android.os.Build
import android.text.Html

fun getStyledTextFromHtml(htmlString: String?): CharSequence {
    if (htmlString == null) return ""
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(htmlString)
    }
}