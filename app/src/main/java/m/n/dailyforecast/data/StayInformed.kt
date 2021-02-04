package m.n.dailyforecast.data


import com.google.gson.annotations.SerializedName

data class StayInformed(
    @SerializedName("blog")
    val blog: String,
    @SerializedName("twitter")
    val twitter: String
)