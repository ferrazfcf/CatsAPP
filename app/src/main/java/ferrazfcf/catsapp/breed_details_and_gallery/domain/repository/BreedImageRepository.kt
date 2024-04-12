package ferrazfcf.catsapp.breed_details_and_gallery.domain.repository

import ferrazfcf.catsapp.core.data.dto.ImageDTO
import ferrazfcf.catsapp.core.data.params.PaginationParams

interface BreedImageRepository {
    suspend fun getBreedImageList(breed: String, paginationParams: PaginationParams): List<ImageDTO>
}
