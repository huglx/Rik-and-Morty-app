package cz.cvut.fit.biand.homework2.core.di

import cz.cvut.fit.biand.homework2.core.data.api.RetrofitProvider
import cz.cvut.fit.biand.homework2.core.data.db.CharactersDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule get() = module {
    single {RetrofitProvider.provide()}
    single {CharactersDB.instance(androidContext())}
}