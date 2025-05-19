package br.com.hifood.domain.usecase

import br.com.hifood.domain.repository.RestaurantRepository

class ToggleFavoriteUseCase(private val repository: RestaurantRepository) {

    suspend operator fun invoke(restaurantId: String): Boolean {
        return repository.toggleFavorite(restaurantId)
    }
}
