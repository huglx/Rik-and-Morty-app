package cz.cvut.fit.biand.homework2.features.search.di

import cz.cvut.fit.biand.homework2.features.search.data.SearchRemoteDataSource
import cz.cvut.fit.biand.homework2.features.search.data.remote.SearchApiDescription
import cz.cvut.fit.biand.homework2.features.search.data.remote.SearchRetrofitDataSource
import cz.cvut.fit.biand.homework2.features.search.data.SearchRepository
import cz.cvut.fit.biand.homework2.features.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

val searchModule get() = module {
    single { get<Retrofit>().create(SearchApiDescription::class.java) }

    factory<SearchRemoteDataSource> { SearchRetrofitDataSource(get()) }

    factoryOf(::SearchRepository)
    viewModelOf(::SearchViewModel)
}