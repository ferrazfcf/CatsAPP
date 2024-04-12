package ferrazfcf.catsapp.breed_details_and_gallery.presentation

import androidx.annotation.StringRes
import ferrazfcf.catsapp.breed_details_and_gallery.domain.model.BreedInfo
import ferrazfcf.catsapp.core.domain.model.Image

data class BreedDetailsAndGalleryState(
    val breed: String? = null,
    val breedInfo: BreedInfo? = null,
    val isBreedInfoLoading: Boolean = false,
    @StringRes val breedInfoError: Int? = null,
    val gallery: List<Image> = emptyList(),
    val isGalleryLoading: Boolean = false,
    @StringRes val galleryError: Int? = null
)
