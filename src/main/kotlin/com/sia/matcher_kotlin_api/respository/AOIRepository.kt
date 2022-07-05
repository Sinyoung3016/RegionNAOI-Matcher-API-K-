package com.sia.matcher_kotlin_api.respository

import com.sia.matcher_kotlin_api.domain.entity.AOI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface AOIRepository: JpaRepository<AOI, Long> {
    @Query(value = "SELECT * from aoi, region where region.id = :id and ST_Covers(" + "region.area, aoi.area)", nativeQuery = true)
    fun findAllAOIByRegionId(@Param("id") id: Long): List<AOI>
}
