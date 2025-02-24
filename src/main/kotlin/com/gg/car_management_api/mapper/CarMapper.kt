package com.gg.car_management_api.mapper

import com.gg.car_management_api.entity.Car
import com.gg.car_management_api.model.createcar.CreateCarRequest
import com.gg.car_management_api.model.createcar.CreateCarResponse
import com.gg.car_management_api.model.getcar.GetCarResponse
import com.gg.car_management_api.model.updatecar.UpdateCarRequest
import com.gg.car_management_api.model.updatecar.UpdateCarResponse

class CarMapper {
    companion object {
        fun toEntity(request: CreateCarRequest): Car = Car(
            brand = request.brand,
            model = request.model,
            color = request.color,
            yearOfProduction = request.yearOfProduction,
            price = request.price
        )

        fun toGetCarResponse(car: Car): GetCarResponse = GetCarResponse(
            id = car.id,
            brand = car.brand,
            model = car.model,
            color = car.color,
            yearOfProduction = car.yearOfProduction,
            price = car.price
        )

        fun toCreateCarResponse(car: Car): CreateCarResponse = CreateCarResponse(
            id = car.id,
            brand = car.brand,
            model = car.model,
            color = car.color,
            yearOfProduction = car.yearOfProduction,
            price = car.price
        )

        fun toUpdateCarResponse(car: Car): UpdateCarResponse = UpdateCarResponse(
            id = car.id,
            brand = car.brand,
            model = car.model,
            color = car.color,
            yearOfProduction = car.yearOfProduction,
            price = car.price
        )

        fun updateFromRequest(car: Car, request: UpdateCarRequest): Car = car.copy(
            brand = request.brand,
            model = request.model,
            color = request.color,
            yearOfProduction = request.yearOfProduction,
            price = request.price
        )
    }
}