package ferrazfcf.catsapp.breed_list.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ferrazfcf.catsapp.core.data.dto.ImageDTO

@JsonClass(generateAdapter = true)
data class BreedItemDTO(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "origin") val origin: String?,
    @Json(name = "image") val image: ImageDTO?
)
