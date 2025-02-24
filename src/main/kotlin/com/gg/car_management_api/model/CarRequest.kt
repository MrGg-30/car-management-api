package com.gg.car_management_api.model

data class CarRequest(
    val brand: String,
    val model: String,
    val color: String,
    val yearOfProduction: Int,
    val price: Float
)
