package com.gg.car_management_api.model.getcar

data class GetCarResponse(
    val id: Long?,
    val brand: String,
    val model: String,
    val color: String,
    val yearOfProduction: Int,
    val price: Float
)