package com.sia.matcher_kotlin_api.fixture

import com.sia.matcher_kotlin_api.controller.dto.request.AreaSaveRequest
import com.sia.matcher_kotlin_api.domain.Point
import com.sia.matcher_kotlin_api.domain.entity.AOI
import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto
import com.sia.matcher_kotlin_api.service.dto.AreaSaveDto

object AreaForTest {
    val name = "test"
    val points = listOf(Point(0.0, 0.0), Point(0.0, 1.0), Point(1.0, 1.0), Point(1.0, 0.0))
    val pointsOfMap = listOf(
        mapOf("x" to 0, "y" to 0),
        mapOf("x" to 1, "y" to 0),
        mapOf("x" to 1, "y" to 1),
        mapOf("x" to 0, "y" to 1)
    )
    val aoiSaveRequest = AreaSaveRequest(name, points)
    val aoiSaveDto = AreaSaveDto(aoiSaveRequest)
    val aoi = AOI(aoiSaveDto)
    val listOfAOI = listOf(aoi, aoi, aoi)
    val aoiReturnDto = AreaReturnDto(aoi)
    val listOfAOIReturnDto = listOf(aoiReturnDto, aoiReturnDto, aoiReturnDto)
}