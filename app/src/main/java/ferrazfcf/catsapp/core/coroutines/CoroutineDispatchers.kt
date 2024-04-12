package ferrazfcf.catsapp.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface CoroutineDispatchers {
    val main: MainCoroutineDispatcher
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
}
