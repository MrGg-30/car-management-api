package com.gg.car_management_api.entity

import jakarta.persistence.*

@Entity
data class Car(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val brand: String,

    @Column(nullable = false)
    val model: String,

    @Column(nullable = false)
    val color: String,

    @Column(nullable = false)
    val yearOfProduction: Int,

    @Column(nullable = false)
    val price: Float
)