package m.n.dailyforecast.data


import com.google.gson.annotations.SerializedName

data class Rate(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("remaining")
    val remaining: Int,
    @SerializedName("reset")
    val reset: Int
)