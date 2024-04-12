package ferrazfcf.catsapp.breed_details_and_gallery.domain.repository

import ferrazfcf.catsapp.breed_details_and_gallery.data.dto.BreedInfoDTO

interface BreedInfoRepository {
    suspend fun getBreedInfo(breed: String): BreedInfoDTO
}
