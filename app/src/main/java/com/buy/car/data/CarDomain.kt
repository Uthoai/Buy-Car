package com.buy.car.data

import java.io.Serializable

data class CarDomain(
    val description: String = "",
    val picUrl: String = "",
    val engineOutput: String = "",
    val highestSpeed: String = "",
    val price: Int = 0,
    val rating: Double = 0.0,
    val totalCapacity: String = "",
    val title: String = ""
): Serializable
