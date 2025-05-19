package br.com.hifood.di

import br.com.hifood.data.repository.RestaurantRepositoryImpl
import br.com.hifood.domain.repository.RestaurantRepository
import br.com.hifood.domain.usecase.GetRestaurantsUseCase
import br.com.hifood.domain.usecase.ToggleFavoriteUseCase
import br.com.hifood.presentation.viewmodel.RestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<RestaurantRepository> { RestaurantRepositoryImpl() }
    
    factory { GetRestaurantsUseCase(get()) }
    factory { ToggleFavoriteUseCase(get()) }
    
    viewModel { RestaurantViewModel(get(), get()) }
}
