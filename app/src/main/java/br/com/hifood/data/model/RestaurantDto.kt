package br.com.hifood.data.model

import br.com.hifood.domain.model.Restaurant

data class RestaurantDto(
    val id: String,
    val name: String,
    val category: String,
    val rating: Float,
    val distance: Float,
    val deliveryTimeMin: Int,
    val deliveryTimeMax: Int,
    val isFreeDelivery: Boolean,
    val logoUrl: String,
    val location: String,
    val isSponsored: Boolean = false,
    val hasClubDiscount: Boolean = false,
    val discountValue: String? = null,
    val isFavorite: Boolean = false
) {
    fun toDomain(): Restaurant {
        return Restaurant(
            id = id,
            name = name,
            category = category,
            rating = rating,
            distance = distance,
            deliveryTime = "$deliveryTimeMin-$deliveryTimeMax min",
            isFreeDelivery = isFreeDelivery,
            logoUrl = logoUrl,
            location = location,
            isSponsored = isSponsored,
            hasClubDiscount = hasClubDiscount,
            discountValue = discountValue,
            isFavorite = isFavorite
        )
    }
}
