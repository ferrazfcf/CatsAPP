package ferrazfcf.catsapp.core.data.remote

import ferrazfcf.catsapp.di.qualifier.ApiKey
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeadersInterceptor @Inject constructor(
    @ApiKey private val apiKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("x-api-key", apiKey)
            .build()
            .let(chain::proceed)
    }
}
