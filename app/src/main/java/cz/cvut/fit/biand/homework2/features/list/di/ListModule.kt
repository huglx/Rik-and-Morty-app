package cz.cvut.fit.biand.homework2.features.list.di

import cz.cvut.fit.biand.homework2.core.data.db.CharactersDB
import cz.cvut.fit.biand.homework2.features.list.data.CharactersLocalDataSource
import cz.cvut.fit.biand.homework2.features.list.data.CharactersRemoteDataSource
import cz.cvut.fit.biand.homework2.features.list.data.remote.CharactersApiDescription
import cz.cvut.fit.biand.homework2.features.list.data.remote.CharactersRetrofitDataSource
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import cz.cvut.fit.biand.homework2.features.list.presentation.ListViewModel
import cz.cvut.fit.biand.homework2.features.list.data.CharactersRepository
import cz.cvut.fit.biand.homework2.features.list.data.local.CharactersRoomDataSource
import org.koin.core.module.dsl.factoryOf
import retrofit2.Retrofit

val listModule get() = module {
    single { get<Retrofit>().create(CharactersApiDescription::class.java) }
    single { get<CharactersDB>().listDB() }

    factory<CharactersRemoteDataSource> { CharactersRetrofitDataSource(get()) }
    factory<CharactersLocalDataSource> { CharactersRoomDataSource(get()) }

    factoryOf(::CharactersRepository)
    viewModelOf(::ListViewModel)
}