package ferrazfcf.catsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ferrazfcf.catsapp.breed_details_and_gallery.domain.usecase.GetBreedGallery
import ferrazfcf.catsapp.breed_details_and_gallery.domain.usecase.GetBreedGalleryImpl
import ferrazfcf.catsapp.breed_details_and_gallery.domain.usecase.GetBreedInfo
import ferrazfcf.catsapp.breed_details_and_gallery.domain.usecase.GetBreedInfoImpl

@Module
@InstallIn(ViewModelComponent::class)
interface BreedDetailsAndGalleryModule {

    @Binds
    @ViewModelScoped
    fun bindGetBreedInfo(getBreedInfo: GetBreedInfoImpl): GetBreedInfo

    @Binds
    @ViewModelScoped
    fun bindGetBreedGallery(getBreedGallery: GetBreedGalleryImpl): GetBreedGallery
}
