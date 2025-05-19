package br.com.hifood.domain.usecase

import androidx.paging.PagingData
import br.com.hifood.domain.model.Restaurant
import br.com.hifood.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow

class GetRestaurantsUseCase(private val repository: RestaurantRepository) {

    operator fun invoke(searchQuery: String = ""): Flow<PagingData<Restaurant>> {
        return repository.getRestaurants(searchQuery)
    }
}
