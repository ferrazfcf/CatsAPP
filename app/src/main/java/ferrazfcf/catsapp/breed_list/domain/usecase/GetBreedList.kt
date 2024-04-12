package ferrazfcf.catsapp.breed_list.domain.usecase

import ferrazfcf.catsapp.breed_list.domain.model.BreedItem
import ferrazfcf.catsapp.core.domain.util.Resource

interface GetBreedList {
    suspend operator fun invoke(): Resource<List<BreedItem>>
}
