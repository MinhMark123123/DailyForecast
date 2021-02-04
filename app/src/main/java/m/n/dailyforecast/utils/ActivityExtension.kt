package m.n.dailyforecast.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

class ActivityExtension

fun Activity.hideKeyBoard() {
    val inputMethodManager: InputMethodManager = this.getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        this.currentFocus?.windowToken, 0
    )
}