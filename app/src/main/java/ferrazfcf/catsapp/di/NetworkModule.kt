package ferrazfcf.catsapp.di

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ferrazfcf.catsapp.core.data.remote.HeadersInterceptor
import ferrazfcf.catsapp.core.data.remote.Service
import ferrazfcf.catsapp.core.data.remote.ServiceProvider
import ferrazfcf.catsapp.di.qualifier.ApiKey
import ferrazfcf.catsapp.di.qualifier.BaseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @ApiKey
    @Provides
    fun provideApiKey(): String {
        return "live_KLibkdShP4mBQ3XA82Vl3T7SpUOsJ2YMAf8G8P74bjLYNmXUq89yx1t9omnuVdzv"
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String {
        return "https://api.thecatapi.com"
    }

    @Provides
    fun provideLoggerInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor.Logger { message ->
            Log.d("HTTP_LOGGER", message)
        }
        return HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideHttpClient(
        headers: HeadersInterceptor,
        logger: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headers)
            .addInterceptor(logger)
            .build()
    }

    @Provides
    fun provideMoshiConverter(): MoshiConverterFactory {
        val moshi: Moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideService(provider: ServiceProvider): Service {
        return provider.provideService()
    }
}
