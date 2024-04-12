package ferrazfcf.catsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ferrazfcf.catsapp.breed_details_and_gallery.data.repository.BreedImageRepositoryImpl
import ferrazfcf.catsapp.breed_details_and_gallery.data.repository.BreedInfoRepositoryImpl
import ferrazfcf.catsapp.breed_details_and_gallery.domain.repository.BreedImageRepository
import ferrazfcf.catsapp.breed_details_and_gallery.domain.repository.BreedInfoRepository
import ferrazfcf.catsapp.breed_list.data.repository.BreedItemRepositoryImpl
import ferrazfcf.catsapp.breed_list.domain.repository.BreedItemRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindBreedItemRepository(repository: BreedItemRepositoryImpl): BreedItemRepository

    @Binds
    @Singleton
    fun bindBreedInfoRepository(repository: BreedInfoRepositoryImpl): BreedInfoRepository

    @Binds
    @Singleton
    fun bindBreedImageRepository(repository: BreedImageRepositoryImpl): BreedImageRepository
}
