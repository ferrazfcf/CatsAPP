package ferrazfcf.catsapp.breed_details_and_gallery.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ferrazfcf.catsapp.core.data.dto.WeightDTO

@JsonClass(generateAdapter = true)
data class BreedInfoDTO(
    @Json(name = "weight") val weight: WeightDTO?,
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "cfa_url") val cfaUrl: String?,
    @Json(name = "vetstreet_url") val vetstreetUrl: String?,
    @Json(name = "vcahospitals_url") val vcahospitalsUrl: String?,
    @Json(name = "wikipedia_url") val wikipediaUrl: String?,
    @Json(name = "temperament") val temperament: String?,
    @Json(name = "origin") val origin: String?,
    @Json(name = "country_codes") val countryCodes: String?,
    @Json(name = "country_code") val countryCode: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "life_span") val lifeSpan: String?,
    @Json(name = "alt_names") val altNames: String?,
    @Json(name = "adaptability") val adaptability: Int?,
    @Json(name = "affection_level") val affectionLevel: Int?,
    @Json(name = "child_friendly") val childFriendly: Int?,
    @Json(name = "dog_friendly") val dogFriendly: Int?,
    @Json(name = "energy_level") val energyLevel: Int?,
    @Json(name = "grooming") val grooming: Int?,
    @Json(name = "health_issues") val healthIssues: Int?,
    @Json(name = "intelligence") val intelligence: Int?,
    @Json(name = "shedding_level") val sheddingLevel: Int?,
    @Json(name = "social_needs") val socialNeeds: Int?,
    @Json(name = "stranger_friendly") val strangerFriendly: Int?,
    @Json(name = "vocalisation") val vocalisation: Int?
)
