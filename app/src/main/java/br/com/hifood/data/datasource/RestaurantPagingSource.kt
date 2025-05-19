package br.com.hifood.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.hifood.data.model.RestaurantDto
import kotlinx.coroutines.delay
import kotlin.random.Random

class RestaurantPagingSource(
    private val searchQuery: String = "",
    private val networkDelayMillis: Long = 1500
) : PagingSource<Int, RestaurantDto>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 0
        private const val PAGE_SIZE = 5
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RestaurantDto> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            delay(networkDelayMillis)
            
            val restaurants = getMockRestaurants().filter {
                if (searchQuery.isEmpty()) true
                else it.name.contains(searchQuery, ignoreCase = true) || 
                     it.category.contains(searchQuery, ignoreCase = true) ||
                     it.location.contains(searchQuery, ignoreCase = true)
            }
            
            val startIndex = page * PAGE_SIZE
            val endIndex = minOf((page + 1) * PAGE_SIZE, restaurants.size)
            
            val data = if (startIndex < restaurants.size) {
                restaurants.subList(startIndex, endIndex)
            } else {
                emptyList()
            }
            
            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (endIndex < restaurants.size) page + 1 else null
            
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RestaurantDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private fun getMockRestaurants(): List<RestaurantDto> {
        return listOf(
            RestaurantDto(
                id = "1",
                name = "Pizza Prime - Jd. Marajoara",
                category = "Pizza",
                rating = 4.7f,
                distance = 3.1f,
                deliveryTimeMin = 35,
                deliveryTimeMax = 45,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Jd. Marajoara",
                isSponsored = true,
                hasClubDiscount = true,
                discountValue = "R$ 10 off com Clube",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "2",
                name = "Pizzaria do Reid",
                category = "Pizza",
                rating = 4.8f,
                distance = 3.2f,
                deliveryTimeMin = 50,
                deliveryTimeMax = 60,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Centro",
                isSponsored = true,
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "3",
                name = "Dr. Sanduba Na Brasa",
                category = "Lanches",
                rating = 4.9f,
                distance = 2.0f,
                deliveryTimeMin = 45,
                deliveryTimeMax = 55,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Centro",
                isSponsored = true,
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "4",
                name = "Meta Pizza - Jd. Marajoara",
                category = "Pizza",
                rating = 4.8f,
                distance = 3.1f,
                deliveryTimeMin = 25,
                deliveryTimeMax = 35,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Jd. Marajoara",
                isSponsored = true,
                hasClubDiscount = true,
                discountValue = "R$ 10 off com Clube",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "5",
                name = "Brabus Pizzaria",
                category = "Pizza",
                rating = 4.0f,
                distance = 1.3f,
                deliveryTimeMin = 49,
                deliveryTimeMax = 59,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Vila Nova",
                isSponsored = true,
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "6",
                name = "Burger King",
                category = "Lanches",
                rating = 4.5f,
                distance = 2.5f,
                deliveryTimeMin = 30,
                deliveryTimeMax = 40,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Centro",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "7",
                name = "Sushi Express",
                category = "Japonesa",
                rating = 4.6f,
                distance = 4.0f,
                deliveryTimeMin = 40,
                deliveryTimeMax = 55,
                isFreeDelivery = false,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Jardim Paulista",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "8",
                name = "Cantina Italiana",
                category = "Italiana",
                rating = 4.7f,
                distance = 3.8f,
                deliveryTimeMin = 45,
                deliveryTimeMax = 60,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Moema",
                hasClubDiscount = true,
                discountValue = "R$ 15 off com Clube",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "9",
                name = "Taco Bell",
                category = "Mexicana",
                rating = 4.3f,
                distance = 5.2f,
                deliveryTimeMin = 35,
                deliveryTimeMax = 50,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Pinheiros",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "10",
                name = "China in Box",
                category = "Chinesa",
                rating = 4.4f,
                distance = 3.5f,
                deliveryTimeMin = 40,
                deliveryTimeMax = 55,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Itaim Bibi",
                hasClubDiscount = true,
                discountValue = "R$ 8 off com Clube",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "11",
                name = "Outback Steakhouse",
                category = "Carnes",
                rating = 4.8f,
                distance = 6.0f,
                deliveryTimeMin = 50,
                deliveryTimeMax = 70,
                isFreeDelivery = false,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Morumbi",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "12",
                name = "Padaria Brasileira",
                category = "Padaria",
                rating = 4.5f,
                distance = 1.8f,
                deliveryTimeMin = 20,
                deliveryTimeMax = 30,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Vila Mariana",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "13",
                name = "Açaí Concept",
                category = "Açaí",
                rating = 4.6f,
                distance = 2.3f,
                deliveryTimeMin = 25,
                deliveryTimeMax = 35,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Brooklin",
                hasClubDiscount = true,
                discountValue = "R$ 5 off com Clube",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "14",
                name = "Spoleto",
                category = "Italiana",
                rating = 4.2f,
                distance = 3.7f,
                deliveryTimeMin = 30,
                deliveryTimeMax = 45,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Jardins",
                isFavorite = Random.nextBoolean()
            ),
            RestaurantDto(
                id = "15",
                name = "Habib's",
                category = "Árabe",
                rating = 4.0f,
                distance = 2.9f,
                deliveryTimeMin = 35,
                deliveryTimeMax = 50,
                isFreeDelivery = true,
                logoUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/fast-food-restaurant-logo%2C-restaurant-logo-design-template-33255790cb8e1186b28609dd9afd4ee6_screen.jpg?ts=1668794604",
                location = "Santana",
                isFavorite = Random.nextBoolean()
            )
        )
    }
}
