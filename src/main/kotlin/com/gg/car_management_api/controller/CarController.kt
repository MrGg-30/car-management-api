package com.gg.car_management_api.controller

import com.gg.car_management_api.entity.Car
import com.gg.car_management_api.model.createcar.CreateCarRequest
import com.gg.car_management_api.model.updatecar.UpdateCarRequest
import com.gg.car_management_api.service.CarService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux

@Controller
class CarController(private val carService: CarService) {

    @QueryMapping
    fun getCarById(@Argument id: Long) = carService.getCarById(id)

    @QueryMapping
    fun getAllCars() = carService.getAllCars()

    @MutationMapping
    fun addCar(@Argument("input") request: CreateCarRequest) = carService.addCar(request)

    @MutationMapping
    fun updateCar(@Argument id: Long, @Argument("input") request: UpdateCarRequest) = carService.updateCar(id, request)

    @MutationMapping
    fun deleteCar(@Argument id: Long) = carService.deleteCar(id)

    @SubscriptionMapping
    fun carUpdates(): Flux<Car> {
        return carService.getCarUpdates()
    }
}
