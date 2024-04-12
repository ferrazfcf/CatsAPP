package ferrazfcf.catsapp.breed_details_and_gallery.domain.model

data class BreedInfo(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val lifeSpan: String,
    val metricWeight: String,
    val imperialWeight: String,
    val adaptability: Int,
    val affectionLevel: Int,
    val childFriendly: Int,
    val dogFriendly: Int,
    val energyLevel: Int,
    val grooming: Int,
    val healthIssues: Int,
    val intelligence: Int,
    val sheddingLevel: Int,
    val socialNeeds: Int,
    val strangerFriendly: Int,
    val vocalisation: Int,
    val cfaUrl: String?,
    val vetstreetUrl: String?,
    val vcahospitalsUrl: String?,
    val wikipediaUrl: String?,
    val altNames: String?
)
