package com.sia.matcher_kotlin_api;

import com.sia.matcher_kotlin_api.controller.dto.request.AreaSaveRequest
import com.sia.matcher_kotlin_api.domain.Point
import com.sia.matcher_kotlin_api.domain.entity.AOI
import com.sia.matcher_kotlin_api.domain.entity.Region
import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto
import com.sia.matcher_kotlin_api.service.dto.AreaSaveDto

class AreaForTest {
    companion object {
        val areaSaveRequest = AreaSaveRequest("hello", listOf(Point(0.0, 0.0), Point(0.0, 1.0), Point(1.0, 1.0), Point(1.0, 0.0)))
        val areaSaveDto = AreaSaveDto(areaSaveRequest)
        val aoi = AOI(areaSaveDto)
        val listOfAOI = listOf(aoi, aoi, aoi)
        val region = Region(areaSaveDto)
        val aoiReturnDto = AreaReturnDto(aoi)
        val listOfAOIReturnDto = listOf(aoiReturnDto, aoiReturnDto, aoiReturnDto)
    }
}