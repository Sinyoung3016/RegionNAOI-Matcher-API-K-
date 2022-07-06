package com.sia.matcher_kotlin_api.controller

import com.sia.matcher_kotlin_api.controller.dto.request.AreaSaveRequest;
import com.sia.matcher_kotlin_api.controller.dto.response.AreaSaveResponse;
import com.sia.matcher_kotlin_api.service.AOIService;
import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto;
import com.sia.matcher_kotlin_api.service.dto.AreaSaveDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aois")
class AOIController(private val aoiService: AOIService) {

    @PostMapping
    fun postAOI(@RequestBody request: AreaSaveRequest) : ResponseEntity<AreaSaveResponse> {
        val returnAOI: AreaReturnDto = aoiService.createNewAOI(AreaSaveDto(request))
        return ResponseEntity.ok(AreaSaveResponse(returnAOI))
    }
}
