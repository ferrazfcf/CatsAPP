package ferrazfcf.catsapp.breed_list.presentation

import androidx.annotation.StringRes
import ferrazfcf.catsapp.breed_list.domain.model.BreedItem

data class BreedListState(
    val isLoading: Boolean = false,
    @StringRes val error: Int? = null,
    val breeds: List<BreedItem> = emptyList()
)
