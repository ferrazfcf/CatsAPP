package ferrazfcf.catsapp.core.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeightDTO(
    @Json(name = "imperial") val imperial: String,
    @Json(name = "metric") val metric: String
)
