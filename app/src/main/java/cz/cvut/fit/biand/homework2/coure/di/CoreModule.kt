package cz.cvut.fit.biand.homework2.coure.di

import cz.cvut.fit.biand.homework2.coure.data.api.RetrofitProvider
import cz.cvut.fit.biand.homework2.coure.data.db.CharactersDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule get() = module {
    single {RetrofitProvider.provide()}
    single {CharactersDB.instance(androidContext())}
}