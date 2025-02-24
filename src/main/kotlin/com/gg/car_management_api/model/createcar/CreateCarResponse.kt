package com.gg.car_management_api.model.createcar

data class CreateCarResponse(
    val id: Long?,
    val brand: String,
    val model: String,
    val color: String,
    val yearOfProduction: Int,
    val price: Float
    )