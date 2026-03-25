package com.fadymarty.gametime.domain.model

data class CircleTemplate(
    val diameter: Int,
    val borderWidth: Int,
)

val circleTemplates = listOf(
    CircleTemplate(
        diameter = 150,
        borderWidth = 15
    ),
    CircleTemplate(
        diameter = 110,
        borderWidth = 6
    ),
    CircleTemplate(
        diameter = 89,
        borderWidth = 6
    ),
    CircleTemplate(
        diameter = 72,
        borderWidth = 3
    ),
    CircleTemplate(
        diameter = 61,
        borderWidth = 3
    ),
    CircleTemplate(
        diameter = 49,
        borderWidth = 3
    ),
    CircleTemplate(
        diameter = 38,
        borderWidth = 3
    ),
    CircleTemplate(
        diameter = 27,
        borderWidth = 3
    ),
    CircleTemplate(
        diameter = 17,
        borderWidth = 3
    )
)