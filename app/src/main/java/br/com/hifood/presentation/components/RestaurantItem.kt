package br.com.hifood.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import br.com.hifood.domain.model.Restaurant

@Composable
fun RestaurantItem(
    restaurant: Restaurant,
    onFavoriteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = restaurant.logoUrl,
                    placeholder = painterResource(id = android.R.drawable.ic_menu_gallery)
                ),
                contentDescription = "${restaurant.name} logo",
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Crop
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            if (restaurant.isSponsored) {
                Text(
                    text = "Patrocinado",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            
            Text(
                text = restaurant.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Color(0xFFFFC107)
                )
                
                Spacer(modifier = Modifier.width(4.dp))
                
                Text(
                    text = restaurant.rating.toString(),
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                
                Text(
                    text = " • ",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                Text(
                    text = restaurant.category,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                
                Text(
                    text = " • ",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                Text(
                    text = "${restaurant.distance} km",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = restaurant.deliveryTime,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                
                Text(
                    text = " • ",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                if (restaurant.isFreeDelivery) {
                    Text(
                        text = "Grátis",
                        fontSize = 14.sp,
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            if (restaurant.hasClubDiscount && restaurant.discountValue != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF9C27B0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "♦",
                            fontSize = 10.sp,
                            color = Color.White
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(4.dp))
                    
                    Text(
                        text = restaurant.discountValue,
                        fontSize = 14.sp,
                        color = Color(0xFF9C27B0)
                    )
                }
            }
        }
        
        IconButton(
            onClick = { onFavoriteClick(restaurant.id) }
        ) {
            Icon(
                imageVector = if (restaurant.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (restaurant.isFavorite) "Remover dos favoritos" else "Adicionar aos favoritos",
                tint = if (restaurant.isFavorite) Color(0xFFE91E63) else Color.Gray
            )
        }
    }
}
