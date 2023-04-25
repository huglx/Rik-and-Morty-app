package cz.cvut.fit.biand.homework2.features.list.di

import cz.cvut.fit.biand.homework2.features.list.data.CharacterRemoteDataSource
import cz.cvut.fit.biand.homework2.features.list.data.remote.CharactersApiDescription
import cz.cvut.fit.biand.homework2.features.list.data.remote.CharacterRetrofitDataSource
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import cz.cvut.fit.biand.homework2.features.list.presentation.ListViewModel
import retrofit2.Retrofit

val listModule get() = module {
    single { get<Retrofit>().create(CharactersApiDescription::class.java) }
    factory<CharacterRemoteDataSource> { CharacterRetrofitDataSource(get()) }


    viewModelOf(::ListViewModel)
}