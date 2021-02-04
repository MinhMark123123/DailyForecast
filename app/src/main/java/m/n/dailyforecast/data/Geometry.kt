package m.n.dailyforecast.data


import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)