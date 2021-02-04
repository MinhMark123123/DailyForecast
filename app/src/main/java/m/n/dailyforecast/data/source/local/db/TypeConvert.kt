package m.n.dailyforecast.data.source.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import m.n.dailyforecast.data.Bounds
import m.n.dailyforecast.data.Components
import m.n.dailyforecast.data.Geometry


class Converters{
    @TypeConverter
    fun fromBounds(bounds: Bounds):String?{
        val type = object : TypeToken<Bounds>() {}.type
        return Gson().toJson(bounds, type)
    }

    @TypeConverter
    fun toBounds(boundsJson : String): Bounds{
        val type = object : TypeToken<Bounds>() {}.type
        return Gson().fromJson<Bounds>(boundsJson, type)
    }
    @TypeConverter
    fun fromComponents(components: Components):String?{
        val type = object : TypeToken<Components>() {}.type
        return Gson().toJson(components, type)
    }

    @TypeConverter
    fun toComponents(components: String): Components{
        val type = object : TypeToken<Components>() {}.type
        return Gson().fromJson<Components>(components, type)
    }

    @TypeConverter
    fun fromGeometry( geometry: Geometry):String?{
        val type = object : TypeToken<Geometry>() {}.type
        return Gson().toJson(geometry, type)
    }

    @TypeConverter
    fun toGeometry(geometry: String): Geometry{
        val type = object : TypeToken<Geometry>() {}.type
        return Gson().fromJson<Geometry>(geometry, type)
    }
}