package ferrazfcf.catsapp.core.domain.util

import androidx.annotation.StringRes
import ferrazfcf.catsapp.core.error.CatsAppException

sealed interface Resource<out T> {
    data class Success<T>(val data: T): Resource<T>
    @JvmInline
    value class Error(
        private val throwable: CatsAppException
    ): Resource<Nothing> {
        @get:StringRes
        val errorMessage: Int
            get() = throwable.errorMessage
    }
}
