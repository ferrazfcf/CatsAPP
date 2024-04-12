package ferrazfcf.catsapp.breed_list.data.repository

import ferrazfcf.catsapp.breed_list.data.dto.BreedItemDTO
import ferrazfcf.catsapp.breed_list.domain.repository.BreedItemRepository
import ferrazfcf.catsapp.core.data.params.PaginationParams
import ferrazfcf.catsapp.core.data.remote.Service
import ferrazfcf.catsapp.core.data.remote.resultOrThrow
import javax.inject.Inject

class BreedItemRepositoryImpl @Inject constructor(
    private val service: Service
) : BreedItemRepository {

    override suspend fun getBreedItemList(params: PaginationParams): List<BreedItemDTO> {
        return resultOrThrow {
            service.requestBreedItemList(params.toMap())
        }
    }
}
