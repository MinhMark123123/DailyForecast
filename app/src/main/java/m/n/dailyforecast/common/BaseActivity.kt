package m.n.dailyforecast.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import m.n.dailyforecast.utils.ConnectionLiveData

abstract class BaseActivity : AppCompatActivity() {
    var networkLive: ConnectionLiveData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkLive = ConnectionLiveData(this)
    }
}