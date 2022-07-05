package com.sia.matcher_kotlin_api.service

import com.sia.matcher_kotlin_api.domain.entity.AOI
import com.sia.matcher_kotlin_api.domain.entity.Region
import com.sia.matcher_kotlin_api.respository.AOIRepository
import com.sia.matcher_kotlin_api.respository.RegionRepository
import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto
import com.sia.matcher_kotlin_api.service.dto.AreaSaveDto
import org.springframework.stereotype.Service

@Service
class RegionService(private val regionRepository: RegionRepository, private val aoiRepository: AOIRepository) {

    fun createNewRegion(requestDto: AreaSaveDto): AreaReturnDto {
        return AreaReturnDto(regionRepository.save(Region(requestDto)))
    }

    fun hasRegionId(regionId: Long) : Boolean {
        return regionRepository.existsById(regionId);
    }

    fun readAllAOIInThisRegion(regionId: Long) : List<AreaReturnDto> {
        val aois: List<AOI>  = aoiRepository.findAllAOIByRegionId(regionId)
        return aois.map { aoi -> AreaReturnDto(aoi) }
    }
}
