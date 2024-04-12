package ferrazfcf.catsapp.breed_details_and_gallery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ferrazfcf.catsapp.breed_details_and_gallery.domain.model.BreedInfo
import ferrazfcf.catsapp.breed_details_and_gallery.domain.usecase.GetBreedGallery
import ferrazfcf.catsapp.breed_details_and_gallery.domain.usecase.GetBreedInfo
import ferrazfcf.catsapp.core.coroutines.CoroutineDispatchers
import ferrazfcf.catsapp.core.domain.model.Image
import ferrazfcf.catsapp.core.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailsAndGalleryViewModel @Inject constructor(
    private val getBreedInfo: GetBreedInfo,
    private val getBreedGallery: GetBreedGallery,
    private val dispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow(BreedDetailsAndGalleryState())
    val state: StateFlow<BreedDetailsAndGalleryState> = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        BreedDetailsAndGalleryState()
    )

    fun onEvent(event: BreedDetailsAndGalleryEvent) {
        when (event) {
            BreedDetailsAndGalleryEvent.LoadBreedInfo -> loadBreedInfo(state.value)
            BreedDetailsAndGalleryEvent.LoadBreedGallery -> loadBreedGallery(state.value)
            is BreedDetailsAndGalleryEvent.SetBreed -> initViewModel(event)
            else -> Unit
        }
    }

    private fun initViewModel(event: BreedDetailsAndGalleryEvent.SetBreed) {
        _state.update { state ->
            state.copy(breed = event.breed)
        }
        onEvent(BreedDetailsAndGalleryEvent.LoadBreedInfo)
    }

    private fun loadBreedInfo(stateOnLoadStart: BreedDetailsAndGalleryState) {
        if (stateOnLoadStart.isBreedInfoLoading || stateOnLoadStart.breed.isNullOrBlank()) return
        viewModelScope.launch(dispatchers.io) {
            _state.update { state ->
                state.copy(
                    isBreedInfoLoading = true,
                    breedInfoError = null
                )
            }

            val result = getBreedInfo(stateOnLoadStart.breed)
            _state.update { state ->
                updateStateOnBreedInfoResult(result, state)
            }
        }
    }

    private fun updateStateOnBreedInfoResult(
        result: Resource<BreedInfo>,
        state: BreedDetailsAndGalleryState
    ): BreedDetailsAndGalleryState {
        return when (result) {
            is Resource.Success -> state.copy(
                isBreedInfoLoading = false,
                breedInfo = result.data
            )

            is Resource.Error -> {
                state.copy(
                    isBreedInfoLoading = false,
                    breedInfoError = result.errorMessage
                )
            }
        }
    }

    private fun loadBreedGallery(stateOnLoadStart: BreedDetailsAndGalleryState) {
        if (stateOnLoadStart.isGalleryLoading || stateOnLoadStart.breed.isNullOrBlank()) return
        viewModelScope.launch(dispatchers.io) {
            _state.update { state ->
                state.copy(
                    isGalleryLoading = true,
                    galleryError = null
                )
            }

            val result = getBreedGallery(stateOnLoadStart.breed)
            _state.update { state ->
                updateStateOnBreedGalleryResult(result, state)
            }
        }
    }

    private fun updateStateOnBreedGalleryResult(
        result: Resource<List<Image>>,
        state: BreedDetailsAndGalleryState
    ): BreedDetailsAndGalleryState {
        return when (result) {
            is Resource.Success -> state.copy(
                isGalleryLoading = false,
                gallery = result.data
            )

            is Resource.Error -> {
                state.copy(
                    isGalleryLoading = false,
                    galleryError = result.errorMessage
                )
            }
        }
    }
}
