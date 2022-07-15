package studio.lunabee.kmptest.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import studio.lunabee.kmptest.common.ResourcesProvider
import studio.lunabee.kmptest.presentation.screen.users.UserViewModel

val appModule: Module = module {

    viewModel { UserViewModel(get()) }

    factory {
        ResourcesProvider(androidContext())
    }
}