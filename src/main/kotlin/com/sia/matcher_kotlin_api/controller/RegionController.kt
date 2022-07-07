package com.sia.matcher_kotlin_api.controller;

import com.sia.matcher_kotlin_api.controller.dto.request.AreaSaveRequest
import com.sia.matcher_kotlin_api.controller.dto.response.AreaListReturnResponse
import com.sia.matcher_kotlin_api.controller.dto.response.AreaSaveResponse
import com.sia.matcher_kotlin_api.service.RegionService
import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto
import com.sia.matcher_kotlin_api.service.dto.AreaSaveDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/regions")
class RegionController(private val regionService: RegionService) {

    @PostMapping
    fun postRegion(@RequestBody request: AreaSaveRequest): ResponseEntity<AreaSaveResponse>{
        val returnRegion: AreaReturnDto = regionService.createNewRegion(AreaSaveDto(request));
        return ResponseEntity.ok(AreaSaveResponse(returnRegion));
    }

    @GetMapping("/{region-id}/aois/intersects")
    fun getAOIListInThisRegion(@PathVariable("region-id") regionId_:String): ResponseEntity<AreaListReturnResponse>{
        val regionId = regionId_.toLong()
        if (!regionService.hasRegionId(regionId))
            throw RuntimeException("존재하지 않는 ID 입니다")
        val returnAOIList = regionService.readAllAOIInThisRegion(regionId)
        return ResponseEntity.ok(AreaListReturnResponse(returnAOIList))
    }
}
