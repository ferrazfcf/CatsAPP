package ferrazfcf.catsapp.core.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import ferrazfcf.catsapp.R
import ferrazfcf.catsapp.core.theme.CatsAPPTheme

@Composable
fun SimpleListItem(
    modifier: Modifier = Modifier,
    image: String?,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.large
            )
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(onClick = onClick)
    ) {
        val (imageRef, titleRef, descriptionRef) = createRefs()
        CoilImage(
            imageModel = {
                image
            },
            modifier = Modifier
                .size(56.dp)
                .clip(MaterialTheme.shapes.medium)
                .constrainAs(imageRef) {
                    linkTo(parent.top, parent.bottom, 8.dp, 8.dp)
                },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.size(56.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 4.dp
                )
            },
            failure = { Image(imageVector = Icons.Default.Pets, contentDescription = null) },
            previewPlaceholder = R.drawable.ic_launcher_foreground
        )

        Text(
            modifier = Modifier.constrainAs(titleRef) {
                linkTo(imageRef.end, parent.end, 8.dp, 0.dp)
                linkTo(parent.top, descriptionRef.top)
                width = Dimension.fillToConstraints
            },
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier.constrainAs(descriptionRef) {
                linkTo(titleRef.start, titleRef.end)
                linkTo(titleRef.bottom, parent.bottom)
                width = Dimension.fillToConstraints
            },
            text = description,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SimpleListItemPreview() {
    CatsAPPTheme {
        SimpleListItem(
            modifier = Modifier.fillMaxWidth(),
            image = null,
            title = "Abyssinian",
            description = "Egypt",
            onClick = { }
        )
    }
}
