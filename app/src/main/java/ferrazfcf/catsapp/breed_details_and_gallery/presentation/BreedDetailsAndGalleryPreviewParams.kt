package ferrazfcf.catsapp.breed_details_and_gallery.presentation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ferrazfcf.catsapp.breed_details_and_gallery.domain.model.BreedInfo
import ferrazfcf.catsapp.core.domain.model.Image
import ferrazfcf.catsapp.core.error.CatsAppError

class BreedDetailsAndGalleryPreviewParams : PreviewParameterProvider<BreedDetailsAndGalleryState> {
    override val values: Sequence<BreedDetailsAndGalleryState>
        get() = sequenceOf(
            loadedState,
            loadingState,
            errorState
        )

    companion object {
        private val breedInfo = BreedInfo(
            id = "abys",
            name = "Abyssinian",
            temperament = "Active, Energetic, Independent, Intelligent, Gentle",
            origin = "Egypt",
            description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
            lifeSpan = "14 - 15",
            metricWeight = "3 - 5",
            imperialWeight = "7  -  10",
            adaptability = 5,
            affectionLevel = 5,
            childFriendly = 3,
            dogFriendly = 4,
            energyLevel = 5,
            grooming = 1,
            healthIssues = 2,
            intelligence = 5,
            sheddingLevel = 2,
            socialNeeds = 5,
            strangerFriendly = 5,
            vocalisation = 1,
            cfaUrl = "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
            vetstreetUrl = "http://www.vetstreet.com/cats/abyssinian",
            vcahospitalsUrl = "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
            wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
            altNames = "Abyssinian Alt"
        )
        private val image = Image("https://cdn2.thecatapi.com/images/O3btzLlsO.png")
        private val gallery = buildList {
            repeat(25) {
                add(image)
            }
        }
        val loadedState = BreedDetailsAndGalleryState(
            breed = "abys",
            breedInfo = breedInfo,
            gallery = gallery
        )
        val loadingState = BreedDetailsAndGalleryState(
            breed = "abys",
            isBreedInfoLoading = true,
            isGalleryLoading = true
        )
        val errorState = BreedDetailsAndGalleryState(
            breed = "abys",
            breedInfoError = CatsAppError.UNKNOWN_ERROR.message,
            galleryError = CatsAppError.UNKNOWN_ERROR.message
        )
    }
}
