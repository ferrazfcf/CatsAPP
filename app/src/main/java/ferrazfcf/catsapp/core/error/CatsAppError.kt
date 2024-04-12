package ferrazfcf.catsapp.core.error

import androidx.annotation.StringRes
import ferrazfcf.catsapp.R

enum class CatsAppError(@StringRes val message: Int) {
    SERVICE_UNAVAILABLE(R.string.error_service_unavailable),
    SERVER_ERROR(R.string.server_error),
    CLIENT_ERROR(R.string.client_error),
    UNKNOWN_ERROR(R.string.unknown_error)
}
