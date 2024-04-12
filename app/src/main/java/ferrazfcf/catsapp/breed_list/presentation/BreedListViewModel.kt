package ferrazfcf.catsapp.breed_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ferrazfcf.catsapp.breed_list.domain.model.BreedItem
import ferrazfcf.catsapp.breed_list.domain.usecase.GetBreedList
import ferrazfcf.catsapp.core.coroutines.CoroutineDispatchers
import ferrazfcf.catsapp.core.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val getBreedList: GetBreedList,
    private val dispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow(BreedListState())
    val state: StateFlow<BreedListState> = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        BreedListState()
    )

    init {
        onEvent(BreedListEvent.LoadBreeds)
    }

    fun onEvent(event: BreedListEvent) {
        when (event) {
            BreedListEvent.LoadBreeds -> loadBreeds(state.value)
            else -> Unit
        }
    }

    private fun loadBreeds(stateOnLoadStart: BreedListState) {
        if (stateOnLoadStart.isLoading) return
        viewModelScope.launch(dispatchers.io) {
            _state.update { state ->
                state.copy(
                    isLoading = true,
                    error = null
                )
            }

            val result = getBreedList()
            _state.update { state ->
                updateStateOnResult(result, state)
            }
        }
    }

    private fun updateStateOnResult(
        result: Resource<List<BreedItem>>,
        state: BreedListState
    ): BreedListState {
        return when (result) {
            is Resource.Success -> state.copy(
                isLoading = false,
                breeds = result.data
            )

            is Resource.Error -> state.copy(
                isLoading = false,
                error = result.errorMessage
            )
        }
    }
}
