package cz.cvut.fit.biand.homework2.features.list.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import cz.cvut.fit.biand.homework2.features.list.presentation.ListViewModel

val listModule get() = module {
    viewModelOf(::ListViewModel)
}