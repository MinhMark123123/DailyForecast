package m.n.dailyforecast.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CityLocation(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("bounds")
    @ColumnInfo(name = "bounds")
    val bounds: Bounds,
    @SerializedName("components")
    @ColumnInfo(name = "components")
    val components: Components,
    @SerializedName("confidence")
    @ColumnInfo(name = "confidence")
    val confidence: Int,
    @SerializedName("formatted")
    @ColumnInfo(name = "formatted")
    val formatted: String,
    @SerializedName("geometry")
    @ColumnInfo(name = "geometry")
    val geometry: Geometry
)