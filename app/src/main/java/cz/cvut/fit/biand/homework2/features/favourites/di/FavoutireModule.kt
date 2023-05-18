package cz.cvut.fit.biand.homework2.features.favourites.di

import cz.cvut.fit.biand.homework2.core.data.db.CharactersDB
import cz.cvut.fit.biand.homework2.features.favourites.data.FavouriteLocalDataSource
import cz.cvut.fit.biand.homework2.features.favourites.data.FavouriteRepository
import cz.cvut.fit.biand.homework2.features.favourites.data.local.FavouriteRoomDataSource
import cz.cvut.fit.biand.homework2.features.favourites.presentation.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val favouriteModule get() = module {
    single { get<CharactersDB>().favouriteDB() }

    factory<FavouriteLocalDataSource> { FavouriteRoomDataSource(get()) }
    factoryOf(::FavouriteRepository)
    viewModelOf(::FavouriteViewModel)
}