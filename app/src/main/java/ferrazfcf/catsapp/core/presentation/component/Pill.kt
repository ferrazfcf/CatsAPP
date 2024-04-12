package ferrazfcf.catsapp.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ferrazfcf.catsapp.R
import ferrazfcf.catsapp.core.theme.CatsAPPTheme

@Composable
fun Pill(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    shape: RoundedCornerShape,
    text: String,
    style: TextStyle,
    color: Color
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = shape)
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = style,
            color = color
        )
    }
}

@Preview
@Composable
private fun PillPreview() {
    CatsAPPTheme {
        Pill(
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(25.dp),
            text = stringResource(id = R.string.adaptability, 5),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
