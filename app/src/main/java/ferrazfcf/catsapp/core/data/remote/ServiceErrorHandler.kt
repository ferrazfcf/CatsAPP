package ferrazfcf.catsapp.core.data.remote

import ferrazfcf.catsapp.core.error.CatsAppError
import ferrazfcf.catsapp.core.error.CatsAppException
import okio.IOException
import retrofit2.Response

suspend fun <T> resultOrThrow(request: suspend () -> Response<T>) : T {
    val result = try {
        request()
    } catch (e: IOException) {
        throw CatsAppException(
            error = CatsAppError.SERVICE_UNAVAILABLE,
            throwable = e
        )
    }

    when (result.code()) {
        in 200..299 -> Unit
        in 400..499 -> throw CatsAppException(CatsAppError.CLIENT_ERROR)
        500 -> throw CatsAppException(CatsAppError.SERVER_ERROR)
        else -> throw CatsAppException(CatsAppError.UNKNOWN_ERROR)
    }

    return try {
        result.body()!!
    } catch (e: Exception) {
        throw CatsAppException(CatsAppError.SERVER_ERROR)
    }
}
