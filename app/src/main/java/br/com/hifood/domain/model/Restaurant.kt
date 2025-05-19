package br.com.hifood.domain.model

data class Restaurant(
    val id: String,
    val name: String,
    val category: String,
    val rating: Float,
    val distance: Float,
    val deliveryTime: String,
    val isFreeDelivery: Boolean,
    val logoUrl: String,
    val location: String,
    val isSponsored: Boolean = false,
    val hasClubDiscount: Boolean = false,
    val discountValue: String? = null,
    val isFavorite: Boolean = false
)
