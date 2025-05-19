package br.com.hifood.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import br.com.hifood.data.datasource.RestaurantPagingSource
import br.com.hifood.domain.model.Restaurant
import br.com.hifood.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RestaurantRepositoryImpl : RestaurantRepository {

    companion object {
        private const val PAGE_SIZE = 5
    }

    override fun getRestaurants(searchQuery: String): Flow<PagingData<Restaurant>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
                maxSize = PAGE_SIZE * 3
            ),
            pagingSourceFactory = { RestaurantPagingSource(searchQuery) }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun toggleFavorite(restaurantId: String): Boolean {
        return true
    }
}
