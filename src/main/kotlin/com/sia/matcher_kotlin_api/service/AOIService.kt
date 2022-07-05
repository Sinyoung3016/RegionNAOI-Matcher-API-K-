package com.sia.matcher_kotlin_api.service

import com.sia.matcher_kotlin_api.domain.entity.AOI
import com.sia.matcher_kotlin_api.respository.AOIRepository
import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto
import com.sia.matcher_kotlin_api.service.dto.AreaSaveDto
import org.springframework.stereotype.Service

@Service
class AOIService(private val aoiRepository:AOIRepository) {

    fun createNewRegion(requestDto: AreaSaveDto) : AreaReturnDto{
       return AreaReturnDto(aoiRepository.save(AOI(requestDto)))
    }
}
