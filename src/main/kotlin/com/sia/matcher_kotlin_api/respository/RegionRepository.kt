package com.sia.matcher_kotlin_api.respository

import com.sia.matcher_kotlin_api.domain.entity.Region
import org.springframework.data.jpa.repository.JpaRepository

interface RegionRepository: JpaRepository<Region, Long>
