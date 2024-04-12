package ferrazfcf.catsapp.breed_details_and_gallery.domain.usecase

import ferrazfcf.catsapp.breed_details_and_gallery.domain.model.BreedInfo
import ferrazfcf.catsapp.core.domain.util.Resource

interface GetBreedInfo {
    suspend operator fun invoke(breed: String): Resource<BreedInfo>
}
