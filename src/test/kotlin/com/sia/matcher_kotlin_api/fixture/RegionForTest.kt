package com.sia.matcher_kotlin_api.fixture;

import com.sia.matcher_kotlin_api.controller.dto.request.AreaSaveRequest
import com.sia.matcher_kotlin_api.domain.Point
import com.sia.matcher_kotlin_api.domain.entity.Region
import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto
import com.sia.matcher_kotlin_api.service.dto.AreaSaveDto

object RegionForTest {
    val name = "test"
    val points = listOf(Point(0.0, 0.0), Point(0.0, 2.0), Point(2.0, 2.0), Point(2.0, 0.0))
    val pointsOfMap = listOf(
        mapOf("x" to 0, "y" to 0),
        mapOf("x" to 2, "y" to 0),
        mapOf("x" to 2, "y" to 2),
        mapOf("x" to 0, "y" to 2)
    )
    val pointsOfMap02 = listOf(
        mapOf("x" to 8, "y" to 8),
        mapOf("x" to 9, "y" to 8),
        mapOf("x" to 10, "y" to 10),
        mapOf("x" to 8, "y" to 10)
    )
    val regionSaveRequest = AreaSaveRequest(name, points)
    val regionSaveDto = AreaSaveDto(regionSaveRequest)
    val region = Region(regionSaveDto)
    val regionReturnDto = AreaReturnDto(region)
}
