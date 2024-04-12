package ferrazfcf.catsapp.breed_list.domain.usecase

import ferrazfcf.catsapp.breed_list.domain.model.BreedItem
import ferrazfcf.catsapp.breed_list.domain.model.toBreedItem
import ferrazfcf.catsapp.breed_list.domain.repository.BreedItemRepository
import ferrazfcf.catsapp.core.data.params.PaginationParams
import ferrazfcf.catsapp.core.domain.util.Resource
import ferrazfcf.catsapp.core.error.CatsAppError
import ferrazfcf.catsapp.core.error.CatsAppException
import ferrazfcf.catsapp.core.logger.LoggerService
import kotlinx.coroutines.ensureActive
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GetBreedListImpl @Inject constructor(
    private val repository: BreedItemRepository,
    private val loggerService: LoggerService
) : GetBreedList {

    override suspend fun invoke(): Resource<List<BreedItem>> {
        return repository.runCatching {
            val breedList = getBreedItemList(PaginationParams())
                .mapNotNull { dto ->
                    dto.toBreedItem(loggerService::sendException)
                }
            coroutineContext.ensureActive()
            Resource.Success(breedList)
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
