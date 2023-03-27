package com.tkitm.recyclerviewdelegate.sample.presentation.utils

import android.text.format.DateFormat
import java.util.*

fun Long.getDateFormat(): CharSequence {
    val calendar = Calendar.getInstance().also {
        it.timeInMillis = this
    }
    return DateFormat.format("dd MMM", calendar)
}