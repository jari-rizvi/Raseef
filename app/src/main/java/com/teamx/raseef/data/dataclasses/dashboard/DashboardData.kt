package com.teamx.raseef.data.dataclasses.dashboard

data class DashboardData(
    val categories: List<Category>,
    val dashboard: Dashboard,
    val popularProducts: List<PopularProduct>,
    val popularShops: List<PopularShop>,
    val topRatedProducts: List<TopRatedProduct>
)