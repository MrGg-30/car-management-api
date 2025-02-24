package com.gg.car_management_api.service

import com.gg.car_management_api.config.CarUpdatesProcessor
import com.gg.car_management_api.entity.Car
import com.gg.car_management_api.exception.CarNotFoundException
import com.gg.car_management_api.exception.CarSaveException
import com.gg.car_management_api.mapper.CarMapper
import com.gg.car_management_api.model.createcar.CreateCarRequest
import com.gg.car_management_api.model.createcar.CreateCarResponse
import com.gg.car_management_api.model.getcar.GetCarResponse
import com.gg.car_management_api.model.updatecar.UpdateCarRequest
import com.gg.car_management_api.model.updatecar.UpdateCarResponse
import com.gg.car_management_api.repository.CarRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks

@Service
class CarService(private val carRepository: CarRepository, private val carUpdatesProcessor: CarUpdatesProcessor) {

    private val sink = Sinks.many().multicast().onBackpressureBuffer<Car>()

    @QueryMapping
    fun getCarById(@Argument id: Long): GetCarResponse =
        carRepository.findById(id)
            .map { CarMapper.toGetCarResponse(it) }
            .orElseThrow { CarNotFoundException("Car with ID $id not found") }

    @MutationMapping
    fun addCar(@Argument("input") request: CreateCarRequest): CreateCarResponse {
        val newCar = CarMapper.toEntity(request)
        return saveCar(newCar) { CarMapper.toCreateCarResponse(it) }
    }

    @MutationMapping
    fun updateCar(@Argument id: Long, @Argument("input") request: UpdateCarRequest): UpdateCarResponse {
        val existingCar = carRepository.findById(id)
            .orElseThrow { CarNotFoundException("Car with ID $id not found") }

        val updatedCar = CarMapper.updateFromRequest(existingCar, request)
        return saveCar(updatedCar) { CarMapper.toUpdateCarResponse(it) }
    }

    @QueryMapping
    fun getAllCars(): List<GetCarResponse> =
        carRepository.findAll().map { CarMapper.toGetCarResponse(it) }

    @MutationMapping
    fun deleteCar(@Argument id: Long): Boolean {
        if (!carRepository.existsById(id)) {
            throw CarNotFoundException("Car with ID $id not found")
        }

        carRepository.deleteById(id)
        return true
    }

    private fun <T> saveCar(car: Car, transformer: (Car) -> T): T {
        val savedCar = carRepository.save(car)
        if (savedCar.id == null) {
            throw CarSaveException("Car could not be saved/updated")
        }

        sink.tryEmitNext(savedCar)
        return transformer(savedCar)
    }

    fun getCarUpdates(): Flux<Car> {
        return carUpdatesProcessor.asFlux()
    }
}
