package studio.lunabee.kmptest.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import studio.lunabee.kmptest.presentation.screen.detail.DetailViewModel
import studio.lunabee.kmptest.presentation.screen.home.HomeViewModel

fun viewModelModule(): Module = module {
    viewModel { DetailViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
}
