package ferrazfcf.catsapp.breed_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import ferrazfcf.catsapp.R
import ferrazfcf.catsapp.core.presentation.component.ErrorRetry
import ferrazfcf.catsapp.core.presentation.component.SimpleListItem
import ferrazfcf.catsapp.core.theme.CatsAPPTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedList(
    state: BreedListState,
    onEvent: (BreedListEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        val layoutDirection = LocalLayoutDirection.current
        val columnPadding = PaddingValues(
            top = innerPadding.calculateTopPadding(),
            bottom = innerPadding.calculateBottomPadding() + 16.dp,
            start = innerPadding.calculateStartPadding(layoutDirection) + 16.dp,
            end = innerPadding.calculateEndPadding(layoutDirection) + 16.dp,
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = columnPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                when {
                    state.isLoading -> Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(80.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 8.dp
                        )
                    }
                    state.error != null -> ErrorRetry(
                        error = stringResource(id = state.error),
                        color = MaterialTheme.colorScheme.onBackground
                    ) {
                        onEvent(BreedListEvent.LoadBreeds)
                    }
                }
            }

            itemsIndexed(
                items = state.breeds,
                key = { index, item ->
                    item.hashCode().times(index)
                }
            ) { _, item ->
                SimpleListItem(
                    modifier = Modifier.fillMaxWidth(),
                    image = item.image,
                    title = item.name,
                    description = stringResource(id = R.string.from, item.origin)
                ) {
                    onEvent(BreedListEvent.ShowBreedDetails(item.id))
                }
            }
        }
    }
}

@Preview
@Composable
private fun BreedListPreview(
    @PreviewParameter(BreedListPreviewParams::class)
    state: BreedListState
) {
    CatsAPPTheme {
        BreedList(state = state) { }
    }
}
