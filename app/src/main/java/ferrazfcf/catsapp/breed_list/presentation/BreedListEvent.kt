package ferrazfcf.catsapp.breed_list.presentation

sealed interface BreedListEvent {
    @JvmInline
    value class ShowBreedDetails(val breed: String): BreedListEvent
    data object LoadBreeds: BreedListEvent
}
