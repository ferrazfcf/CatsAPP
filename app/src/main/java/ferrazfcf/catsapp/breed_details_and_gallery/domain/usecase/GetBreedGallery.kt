package ferrazfcf.catsapp.breed_details_and_gallery.domain.usecase

import ferrazfcf.catsapp.core.domain.model.Image
import ferrazfcf.catsapp.core.domain.util.Resource

interface GetBreedGallery {
    suspend operator fun invoke(breed: String): Resource<List<Image>>
}
