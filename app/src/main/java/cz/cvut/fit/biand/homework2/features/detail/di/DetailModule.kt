package cz.cvut.fit.biand.homework2.features.detail.di

import cz.cvut.fit.biand.homework2.core.data.db.CharactersDB
import cz.cvut.fit.biand.homework2.features.detail.data.DetailLocalDataSource
import cz.cvut.fit.biand.homework2.features.detail.data.local.DetailRoomDataSource
import cz.cvut.fit.biand.homework2.features.detail.data.DetailRepository
import cz.cvut.fit.biand.homework2.features.detail.presentation.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val detailModule get() = module {
    single { get<CharactersDB>().detailDB() }

    factory<DetailLocalDataSource>{ DetailRoomDataSource(get()) }

    factoryOf(::DetailRepository)
    viewModelOf(::DetailViewModel)

}