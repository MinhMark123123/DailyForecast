package m.n.dailyforecast.data


import com.google.gson.annotations.SerializedName

data class Components(
    @SerializedName("_category")
    val category: String,
    @SerializedName("city")
    val city: String?,
    @SerializedName("city_district")
    val cityDistrict: String,
    @SerializedName("continent")
    val continent: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("county")
    val county: String,
    @SerializedName("hamlet")
    val hamlet: String,
    @SerializedName("ISO_3166-1_alpha-2")
    val iSO31661Alpha2: String,
    @SerializedName("ISO_3166-1_alpha-3")
    val iSO31661Alpha3: String,
    @SerializedName("municipality")
    val municipality: String,
    @SerializedName("neighbourhood")
    val neighbourhood: String,
    @SerializedName("postcode")
    val postcode: String,
    @SerializedName("quarter")
    val quarter: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("residential")
    val residential: String,
    @SerializedName("road")
    val road: String,
    @SerializedName("road_type")
    val roadType: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("state_code")
    val stateCode: String,
    @SerializedName("state_district")
    val stateDistrict: String,
    @SerializedName("town")
    val town: String,
    @SerializedName("_type")
    val type: String,
    @SerializedName("village")
    val village: String
)