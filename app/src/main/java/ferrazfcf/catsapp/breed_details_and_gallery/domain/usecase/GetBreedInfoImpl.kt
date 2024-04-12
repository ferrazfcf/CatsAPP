package ferrazfcf.catsapp.breed_details_and_gallery.domain.usecase

import ferrazfcf.catsapp.breed_details_and_gallery.domain.model.BreedInfo
import ferrazfcf.catsapp.breed_details_and_gallery.domain.model.toBreedInfo
import ferrazfcf.catsapp.breed_details_and_gallery.domain.repository.BreedInfoRepository
import ferrazfcf.catsapp.core.domain.util.Resource
import ferrazfcf.catsapp.core.error.CatsAppError
import ferrazfcf.catsapp.core.error.CatsAppException
import ferrazfcf.catsapp.core.logger.LoggerService
import kotlinx.coroutines.ensureActive
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GetBreedInfoImpl @Inject constructor(
    private val repository: BreedInfoRepository,
    private val loggerService: LoggerService
) : GetBreedInfo {

    override suspend fun invoke(breed: String): Resource<BreedInfo> {
        return repository.runCatching {
            val breedInfo = getBreedInfo(breed).toBreedInfo(loggerService::sendException)
                ?: throw CatsAppException(CatsAppError.SERVER_ERROR, NullPointerException())
            coroutineContext.ensureActive()
            Resource.Success(breedInfo)
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
