package ferrazfcf.catsapp.core.domain.model

import ferrazfcf.catsapp.core.data.dto.ImageDTO

fun ImageDTO.toImage(onError: (Throwable) -> Unit): Image? {
    return runCatching {
        requireNotNull(url) { "Image Url should not be null" }
        Image(url)
    }.onFailure(onError).getOrNull()
}
