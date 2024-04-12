package ferrazfcf.catsapp.breed_list.presentation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ferrazfcf.catsapp.breed_list.domain.model.BreedItem
import ferrazfcf.catsapp.core.error.CatsAppError

class BreedListPreviewParams : PreviewParameterProvider<BreedListState> {
    override val values: Sequence<BreedListState>
        get() = sequenceOf(
            loadedState,
            loadingState,
            errorState
        )

    companion object {
        private val breedItem = BreedItem(
            id = "abys",
            name = "Abyssinian",
            origin = "Egypt",
            image = "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg"
        )
        private val breedList = buildList {
            repeat(10) {
                add(breedItem)
            }
        }
        val loadedState = BreedListState(breeds = breedList)
        val loadingState = BreedListState(isLoading = true)
        val errorState = BreedListState(error = CatsAppError.UNKNOWN_ERROR.message)
    }
}
