package com.fadymarty.gametime.domain.model

data class CircleConfig(
    val diameter: Int,
    val borderWidth: Int,
)

val circleConfigs = listOf(
    CircleConfig(
        diameter = 150,
        borderWidth = 15
    ),
    CircleConfig(
        diameter = 110,
        borderWidth = 6
    ),
    CircleConfig(
        diameter = 89,
        borderWidth = 6
    ),
    CircleConfig(
        diameter = 72,
        borderWidth = 3
    ),
    CircleConfig(
        diameter = 61,
        borderWidth = 3
    ),
    CircleConfig(
        diameter = 49,
        borderWidth = 3
    ),
    CircleConfig(
        diameter = 38,
        borderWidth = 3
    ),
    CircleConfig(
        diameter = 27,
        borderWidth = 3
    ),
    CircleConfig(
        diameter = 17,
        borderWidth = 3
    )
)