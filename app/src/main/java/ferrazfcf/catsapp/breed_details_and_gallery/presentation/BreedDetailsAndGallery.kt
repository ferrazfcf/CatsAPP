package ferrazfcf.catsapp.breed_details_and_gallery.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import ferrazfcf.catsapp.R
import ferrazfcf.catsapp.breed_details_and_gallery.domain.model.BreedInfo
import ferrazfcf.catsapp.core.presentation.component.ErrorRetry
import ferrazfcf.catsapp.core.presentation.component.Pill
import ferrazfcf.catsapp.core.presentation.component.UrlText
import ferrazfcf.catsapp.core.theme.CatsAPPTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailsAndGallery(
    state: BreedDetailsAndGalleryState,
    onEvent: (BreedDetailsAndGalleryEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(BreedDetailsAndGalleryEvent.NavigateBack)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                title = {
                    state.breedInfo?.name?.let { name ->
                        Text(
                            text = name,
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(stringResource(id = R.string.gallery)) },
                icon = { Icon(Icons.Default.Image, contentDescription = "") },
                onClick = {
                    onEvent(BreedDetailsAndGalleryEvent.LoadBreedGallery)
                    showBottomSheet = true
                }
            )
        }
    ) { innerPadding ->
        BreedDetails(
            innerPadding,
            state,
            onEvent
        )

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                containerColor = MaterialTheme.colorScheme.background,
                sheetState = sheetState
            ) {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.85f),
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 4.dp,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(
                        items = state.gallery,
                        key = { index, item ->
                            item.hashCode().times(index)
                        }
                    ) { _, item ->
                        CoilImage(
                            imageModel = {
                                item.url
                            },
                            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center
                            ),
                            loading = {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = MaterialTheme.colorScheme.onBackground,
                                    strokeWidth = 4.dp
                                )
                            },
                            failure = { Image(imageVector = Icons.Default.Pets, contentDescription = null) },
                            previewPlaceholder = R.drawable.ic_launcher_foreground
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BreedDetails(
    innerPadding: PaddingValues,
    state: BreedDetailsAndGalleryState,
    onEvent: (BreedDetailsAndGalleryEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.large
            )
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when {
            state.isBreedInfoLoading -> Row(
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
            state.breedInfoError != null -> ErrorRetry(
                error = stringResource(id = state.breedInfoError),
                color = MaterialTheme.colorScheme.onBackground
            ) {
                onEvent(BreedDetailsAndGalleryEvent.LoadBreedInfo)
            }
            state.breedInfo != null -> LoadedBreedDetails(state.breedInfo)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LoadedBreedDetails(breedInfo: BreedInfo) {
    breedInfo.altNames?.let { altName ->
        Text(
            text = stringResource(id = R.string.aka, altName),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

    Text(
        text = stringResource(id = R.string.from, breedInfo.origin),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

    Text(
        text = stringResource(id = R.string.temperament, breedInfo.temperament),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

    Text(
        text = stringResource(id = R.string.description, breedInfo.description),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

    Text(
        text = stringResource(id = R.string.lifespan, breedInfo.lifeSpan),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

    Text(
        text = stringResource(
            id = R.string.weight,
            breedInfo.imperialWeight,
            breedInfo.metricWeight
        ),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

    Text(
        text = stringResource(id = R.string.characteristics),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface
    )

    FlowRow(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.adaptability, breedInfo.adaptability),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.affectionLevel, breedInfo.affectionLevel),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.childFriendly, breedInfo.childFriendly),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.dogFriendly, breedInfo.dogFriendly),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.energyLevel, breedInfo.energyLevel),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.grooming, breedInfo.grooming),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.healthIssues, breedInfo.healthIssues),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.intelligence, breedInfo.intelligence),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.sheddingLevel, breedInfo.sheddingLevel),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.socialNeeds, breedInfo.socialNeeds),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.strangerFriendly, breedInfo.strangerFriendly),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.vocalisation, breedInfo.vocalisation),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
    breedInfo.cfaUrl?.let { url ->
        UrlText(
            url = url,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    breedInfo.vetstreetUrl?.let { url ->
        UrlText(
            url = url,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    breedInfo.vcahospitalsUrl?.let { url ->
        UrlText(
            url = url,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    breedInfo.wikipediaUrl?.let { url ->
        UrlText(
            url = url,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun BreedDetailsAndGalleryPreview(
    @PreviewParameter(BreedDetailsAndGalleryPreviewParams::class)
    state: BreedDetailsAndGalleryState
) {
    CatsAPPTheme {
        BreedDetailsAndGallery(
            state = state,
            onEvent = { }
        )
    }
}
