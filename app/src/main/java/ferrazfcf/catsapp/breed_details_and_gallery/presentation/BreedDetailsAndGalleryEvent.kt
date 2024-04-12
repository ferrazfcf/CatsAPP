package ferrazfcf.catsapp.breed_details_and_gallery.presentation

sealed interface BreedDetailsAndGalleryEvent {
    @JvmInline
    value class SetBreed(val breed: String): BreedDetailsAndGalleryEvent
    data object LoadBreedInfo: BreedDetailsAndGalleryEvent
    data object LoadBreedGallery: BreedDetailsAndGalleryEvent
    data object NavigateBack: BreedDetailsAndGalleryEvent
}
