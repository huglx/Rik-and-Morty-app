package cz.cvut.fit.biand.homework2.features.detail.di

import cz.cvut.fit.biand.homework2.core.data.db.CharactersDB
import cz.cvut.fit.biand.homework2.features.detail.data.DetailLocalDataSource
import cz.cvut.fit.biand.homework2.features.detail.data.DetailRemoteDataSource
import cz.cvut.fit.biand.homework2.features.detail.data.local.DetailRoomDataSource
import cz.cvut.fit.biand.homework2.features.detail.data.DetailRepository
import cz.cvut.fit.biand.homework2.features.detail.data.remote.DetailApiDescription
import cz.cvut.fit.biand.homework2.features.detail.data.remote.DetailRetrofitDataSource
import cz.cvut.fit.biand.homework2.features.detail.presentation.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

val detailModule get() = module {
    single { get<CharactersDB>().detailDB() }
    single { get<Retrofit>().create(DetailApiDescription::class.java) }

    factory<DetailLocalDataSource>{ DetailRoomDataSource(get()) }
    factory<DetailRemoteDataSource>{ DetailRetrofitDataSource(get()) }

    factoryOf(::DetailRepository)
    viewModelOf(::DetailViewModel)

}