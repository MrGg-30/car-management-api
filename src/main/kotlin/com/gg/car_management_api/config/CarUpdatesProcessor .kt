package com.gg.car_management_api.config

import com.gg.car_management_api.entity.Car
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks

@Component
class CarUpdatesProcessor {
    private val sink = Sinks.many().multicast().onBackpressureBuffer<Car>()

    fun asFlux(): Flux<Car> = sink.asFlux()

    fun emit(car: Car) {
        sink.tryEmitNext(car)
    }
}