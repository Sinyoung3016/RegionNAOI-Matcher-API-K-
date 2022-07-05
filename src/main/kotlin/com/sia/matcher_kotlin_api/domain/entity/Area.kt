package com.sia.matcher_kotlin_api.domain.entity

import com.sia.matcher_kotlin_api.service.dto.AreaSaveDto
import org.locationtech.jts.geom.Polygon;
import javax.persistence.*;

@MappedSuperclass
open class Area(requestDto: AreaSaveDto) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length = 20, nullable = false)
    var name: String

    @Column(nullable = false, columnDefinition="geometry")
    var area: Polygon

    init {
        this.name = requestDto.name
        this.area = requestDto.area
    }
}
