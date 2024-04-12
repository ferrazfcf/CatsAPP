package ferrazfcf.catsapp.breed_details_and_gallery.data.repository

import ferrazfcf.catsapp.breed_details_and_gallery.domain.repository.BreedImageRepository
import ferrazfcf.catsapp.core.data.dto.ImageDTO
import ferrazfcf.catsapp.core.data.params.PaginationParams
import ferrazfcf.catsapp.core.data.remote.resultOrThrow
import javax.inject.Inject

class BreedImageRepositoryImpl @Inject constructor(
    private val service: ferrazfcf.catsapp.core.data.remote.Service
) : BreedImageRepository {

    override suspend fun getBreedImageList(
        breed: String,
        paginationParams: PaginationParams
    ): List<ImageDTO> {
        return resultOrThrow {
            val params = paginationParams.toMap()
                .plus("breed_ids" to breed)
            service.requestBreedImageList(params)
        }
    }
}
