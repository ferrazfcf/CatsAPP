package ferrazfcf.catsapp.breed_details_and_gallery.domain.model

import ferrazfcf.catsapp.breed_details_and_gallery.data.dto.BreedInfoDTO

fun BreedInfoDTO.toBreedInfo(onError: (Throwable) -> Unit): BreedInfo? {
    return runCatching {
        BreedInfo(
            id = requireNotNull(id) { "Breed id should not be null" },
            name = requireNotNull(name) { "Breed name should not be null" },
            temperament = requireNotNull(temperament) { "Breed temperament should not be null" },
            origin = requireNotNull(origin) { "Breed origin should not be null" },
            description = requireNotNull(description) { "Breed description should not be null" },
            lifeSpan = requireNotNull(lifeSpan) { "Breed life span should not be null" },
            metricWeight = requireNotNull(weight?.metric) { "Breed metric weight should not be null" },
            imperialWeight = requireNotNull(weight?.imperial) { "Breed imperial weight should not be null" },
            adaptability = requireNotNull(adaptability) { "Breed adaptability should not be null" },
            affectionLevel = requireNotNull(affectionLevel) { "Breed affection level should not be null" },
            childFriendly = requireNotNull(childFriendly) { "Breed child friendly should not be null" },
            dogFriendly = requireNotNull(dogFriendly) { "Breed dog friendly should not be null" },
            energyLevel = requireNotNull(energyLevel) { "Breed energy level should not be null" },
            grooming = requireNotNull(grooming) { "Breed grooming should not be null" },
            healthIssues = requireNotNull(healthIssues) { "Breed health issues should not be null" },
            intelligence = requireNotNull(intelligence) { "Breed intelligence should not be null" },
            sheddingLevel = requireNotNull(sheddingLevel) { "Breed shedding level should not be null" },
            socialNeeds = requireNotNull(socialNeeds) { "Breed social needs should not be null" },
            strangerFriendly = requireNotNull(strangerFriendly) { "Breed stranger friendly should not be null" },
            vocalisation = requireNotNull(vocalisation) { "Breed vocalisation should not be null" },
            cfaUrl = cfaUrl,
            vetstreetUrl = vetstreetUrl,
            vcahospitalsUrl = vcahospitalsUrl,
            wikipediaUrl = wikipediaUrl,
            altNames = altNames?.ifBlank { null }
        )
    }.onFailure(onError).getOrNull()
}
