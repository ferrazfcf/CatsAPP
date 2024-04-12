package ferrazfcf.catsapp.core.logger

import android.util.Log

class FakeLoggerService: LoggerService {

    override fun sendException(throwable: Throwable) {
        // Fake implementation, in an actual project this should send exceptions to the chosen service
        Log.w("LOGGER_SERVICE", "Send Exception", throwable)
    }
}
