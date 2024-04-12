package ferrazfcf.catsapp.breed_details_and_gallery.data.repository

import ferrazfcf.catsapp.breed_details_and_gallery.data.dto.BreedInfoDTO
import ferrazfcf.catsapp.breed_details_and_gallery.domain.repository.BreedInfoRepository
import ferrazfcf.catsapp.core.data.remote.Service
import ferrazfcf.catsapp.core.data.remote.resultOrThrow
import javax.inject.Inject

class BreedInfoRepositoryImpl @Inject constructor(
    private val service: Service
) : BreedInfoRepository {

    override suspend fun getBreedInfo(breed: String): BreedInfoDTO {
        return resultOrThrow {
            service.requestBreedInfo(breed)
        }
    }
}
