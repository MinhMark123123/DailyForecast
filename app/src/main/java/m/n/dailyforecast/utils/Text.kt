package m.n.dailyforecast.utils

import android.content.Context
import android.content.res.Resources
import android.util.Log


fun String.formatSimple(context: Context?, idRes: Int): String {
    context?.let {Log.d("mmmm", "context is null")  }
    val res: Resources? = context?.resources
    res?.let { return String.format(res.getString(idRes), this) }
    return this
}