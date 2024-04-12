package ferrazfcf.catsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ferrazfcf.catsapp.breed_list.domain.usecase.GetBreedList
import ferrazfcf.catsapp.breed_list.domain.usecase.GetBreedListImpl

@Module
@InstallIn(ViewModelComponent::class)
interface BreedListModule {

    @Binds
    @ViewModelScoped
    fun bindGetBreedList(getBreedList: GetBreedListImpl): GetBreedList
}
