package com.gg.car_management_api.model.updatecar

data class UpdateCarRequest(
    val brand: String,
    val model: String,
    val color: String,
    val yearOfProduction: Int,
    val price: Float
)