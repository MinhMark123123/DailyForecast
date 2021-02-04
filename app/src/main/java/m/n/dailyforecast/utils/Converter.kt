package m.n.dailyforecast.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

fun Double.fromFtoC(): Int {
    return (this / 32).roundToInt()
}

fun Int.toDateTime(): String {
    var simpleFormatter = SimpleDateFormat("E, dd MMM yyyy")
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = (this.toLong() * 1000)
    return simpleFormatter.format(calendar.time)
}