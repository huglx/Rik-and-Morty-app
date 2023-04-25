package cz.cvut.fit.biand.homework2.coure.di

import cz.cvut.fit.biand.homework2.coure.data.api.RetrofitProvider
import org.koin.dsl.module

val coreModule get() = module {
    single {RetrofitProvider.provide()}
}