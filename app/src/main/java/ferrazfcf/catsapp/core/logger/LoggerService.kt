package ferrazfcf.catsapp.core.logger

interface LoggerService {
    fun sendException(throwable: Throwable)
}
