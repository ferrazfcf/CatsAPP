package ferrazfcf.catsapp.core.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDTO(
    @Json(name = "url") val url: String?
)
