package ferrazfcf.catsapp.breed_list.domain.repository

import ferrazfcf.catsapp.breed_list.data.dto.BreedItemDTO
import ferrazfcf.catsapp.core.data.params.PaginationParams

interface BreedItemRepository {
    suspend fun getBreedItemList(params: PaginationParams): List<BreedItemDTO>
}
