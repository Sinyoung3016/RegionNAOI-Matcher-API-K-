package com.sia.matcher_kotlin_api.domain.entity

import com.sia.matcher_kotlin_api.service.dto.AreaSaveDto
import javax.persistence.Entity;

@Entity
class AOI(areaSaveDto: AreaSaveDto) : Area(areaSaveDto)
