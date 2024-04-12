package ferrazfcf.catsapp.breed_list.domain.model

import ferrazfcf.catsapp.breed_list.data.dto.BreedItemDTO

fun BreedItemDTO.toBreedItem(onError: (Throwable) -> Unit): BreedItem? {
    return runCatching {
        requireNotNull(id) { "Breed id should not be null" }
        requireNotNull(name) { "Breed name should not be null" }
        requireNotNull(origin) { "Breed origin should not be null" }
        BreedItem(
            id = id,
            name = name,
            origin = origin,
            image = image?.url
        )
    }.onFailure(onError).getOrNull()
}
