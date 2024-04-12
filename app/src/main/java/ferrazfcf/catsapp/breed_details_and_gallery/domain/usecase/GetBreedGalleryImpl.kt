package ferrazfcf.catsapp.breed_details_and_gallery.domain.usecase

import ferrazfcf.catsapp.breed_details_and_gallery.domain.repository.BreedImageRepository
import ferrazfcf.catsapp.core.data.params.PaginationParams
import ferrazfcf.catsapp.core.domain.model.Image
import ferrazfcf.catsapp.core.domain.model.toImage
import ferrazfcf.catsapp.core.domain.util.Resource
import ferrazfcf.catsapp.core.error.CatsAppError
import ferrazfcf.catsapp.core.error.CatsAppException
import ferrazfcf.catsapp.core.logger.LoggerService
import kotlinx.coroutines.ensureActive
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GetBreedGalleryImpl @Inject constructor(
    private val repository: BreedImageRepository,
    private val loggerService: LoggerService
) : GetBreedGallery {
    override suspend fun invoke(breed: String): Resource<List<Image>> {
        return repository.runCatching {
            val gallery = getBreedImageList(breed, PaginationParams()).mapNotNull { dto ->
                dto.toImage(loggerService::sendException)
            }
            coroutineContext.ensureActive()
            Resource.Success(gallery)
        }.getOrElse { throwable ->
            val error = if (throwable is CatsAppException) {
                throwable
            } else {
                CatsAppException(CatsAppError.UNKNOWN_ERROR, throwable)
            }
            Resource.Error(error)
        }
    }
}
