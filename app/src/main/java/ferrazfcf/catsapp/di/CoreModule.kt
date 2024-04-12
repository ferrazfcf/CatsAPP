package ferrazfcf.catsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ferrazfcf.catsapp.core.coroutines.CoroutineDispatchers
import ferrazfcf.catsapp.core.coroutines.RuntimeCoroutineDispatchers
import ferrazfcf.catsapp.core.logger.FakeLoggerService
import ferrazfcf.catsapp.core.logger.LoggerService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatchers(): CoroutineDispatchers {
        return RuntimeCoroutineDispatchers()
    }

    @Provides
    @Singleton
    fun provideLoggerService(): LoggerService {
        return FakeLoggerService()
    }
}
