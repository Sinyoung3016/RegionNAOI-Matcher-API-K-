package com.sia.matcher_kotlin_api.service.dto

import com.sia.matcher_kotlin_api.controller.dto.request.AreaSaveRequest
import com.sia.matcher_kotlin_api.domain.Point
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Polygon

class AreaSaveDto(request: AreaSaveRequest) {
    var name: String = request.name
    var area: Polygon = list2Polygon(request.area)

    private fun list2Polygon(area: List<Point>): Polygon{
        val lenOfPolygon: Int = area.size
        val coordinates = arrayOfNulls<Coordinate>(lenOfPolygon + 1)
        for (i in 0 until lenOfPolygon) {
            coordinates[i] = Coordinate(area[i].x, area[i].y)
        }
        coordinates[lenOfPolygon] = coordinates[0]
        return GeometryFactory().createPolygon(coordinates)
    }
}
