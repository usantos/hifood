package br.com.hifood.domain.repository

import androidx.paging.PagingData
import br.com.hifood.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    fun getRestaurants(searchQuery: String = ""): Flow<PagingData<Restaurant>>

    suspend fun toggleFavorite(restaurantId: String): Boolean
}
