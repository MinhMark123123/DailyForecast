package m.n.dailyforecast.data


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
)