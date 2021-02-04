package m.n.dailyforecast.data


import com.google.gson.annotations.SerializedName

data class OCLocationResponse(
    @SerializedName("documentation")
    val documentation: String,
    @SerializedName("licenses")
    val licenses: List<License>,
    @SerializedName("rate")
    val rate: Rate,
    @SerializedName("results")
    val cityLocations: List<CityLocation>,
    @SerializedName("status")
    val status: Status,
    @SerializedName("stay_informed")
    val stayInformed: StayInformed,
    @SerializedName("thanks")
    val thanks: String,
    @SerializedName("timestamp")
    val timestamp: Timestamp,
    @SerializedName("total_results")
    val totalResults: Int
)