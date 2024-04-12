package ferrazfcf.catsapp.core.error

import androidx.annotation.StringRes

class CatsAppException(
    val error: CatsAppError,
    throwable: Throwable? = null
) : Throwable(
    message = "An error occurred: $error",
    cause = throwable
) {

    @get:StringRes
    val errorMessage: Int
        get() = error.message
}
