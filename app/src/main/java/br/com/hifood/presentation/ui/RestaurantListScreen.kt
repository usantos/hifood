package br.com.hifood.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import br.com.hifood.R
import br.com.hifood.presentation.components.RestaurantItem
import br.com.hifood.presentation.components.RestaurantItemSkeleton
import br.com.hifood.presentation.components.SearchBar
import br.com.hifood.presentation.viewmodel.RestaurantViewModel

@Composable
fun RestaurantListScreen(
    viewModel: RestaurantViewModel
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val restaurants = viewModel.restaurants.collectAsLazyPagingItems()
    
    Scaffold(
        topBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.address_label),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = stringResource(R.string.location_expand_content_desc)
                        )
                    }
                    
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            ) {
                                Text("1")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = stringResource(R.string.notifications_content_desc),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    item {
                        Spacer(modifier = Modifier.width(8.dp))
                        CategoryItem(
                            icon = Icons.Rounded.Search,
                            name = "",
                            isSelected = false
                        )
                    }
                    
                    item {
                        CategoryItem(
                            icon = Icons.Rounded.Place,
                            name = "Restaurantes",
                            isSelected = true
                        )
                    }
                    
                    item {
                        CategoryItem(
                            icon = Icons.Rounded.ShoppingCart,
                            name = "Mercados",
                            isSelected = false
                        )
                    }
                    
                    item {
                        CategoryItem(
                            icon = Icons.Rounded.ShoppingCart,
                            name = "Farmácias",
                            isSelected = false
                        )
                    }
                }
                
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.width(8.dp))
                        FilterChip(
                            text = "Ordenar",
                            hasDropdown = true
                        )
                    }
                    
                    item {
                        FilterChip(
                            text = "Entrega Grátis",
                            hasDropdown = false
                        )
                    }
                    
                    item {
                        FilterChip(
                            text = "Vale-refeição",
                            hasDropdown = true
                        )
                    }
                }

                HorizontalDivider()
                
                Text(
                    text = "Lojas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { viewModel.updateSearchQuery(it) },
                    onSearch = { }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = restaurants.itemCount,
                    key = restaurants.itemKey { it.id }
                ) { index ->
                    val restaurant = restaurants[index]
                    if (restaurant != null) {
                        RestaurantItem(
                            restaurant = restaurant,
                            onFavoriteClick = { viewModel.toggleFavorite(it) }
                        )
                        
                        if (index < restaurants.itemCount - 1) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                color = Color.LightGray.copy(alpha = 0.5f)
                            )
                        }
                    }
                }
                
                when (restaurants.loadState.refresh) {
                    is LoadState.Loading -> {
                        items(5) {
                            RestaurantItemSkeleton()
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                color = Color.LightGray.copy(alpha = 0.5f)
                            )
                        }
                    }
                    is LoadState.Error -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Erro ao carregar restaurantes",
                                    color = Color.Red
                                )
                                TextButton(onClick = { restaurants.refresh() }) {
                                    Text("Tentar novamente")
                                }
                            }
                        }
                    }
                    else -> {}
                }
                
                when (restaurants.loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    is LoadState.Error -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Erro ao carregar mais restaurantes",
                                    color = Color.Red
                                )
                                TextButton(onClick = { restaurants.retry() }) {
                                    Text("Tentar novamente")
                                }
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    icon: ImageVector,
    name: String,
    isSelected: Boolean
) {
    Card(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFEEEEEE) else Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = name
            )
            
            if (name.isNotEmpty()) {
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = name,
                    fontSize = 14.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun FilterChip(
    text: String,
    hasDropdown: Boolean
) {
    Card(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 14.sp
            )
            
            if (hasDropdown) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Expandir",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
