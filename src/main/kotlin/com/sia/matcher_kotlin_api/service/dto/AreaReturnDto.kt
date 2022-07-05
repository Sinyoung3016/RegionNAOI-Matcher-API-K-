package com.sia.matcher_kotlin_api.service.dto

import com.sia.matcher_kotlin_api.domain.Point
import com.sia.matcher_kotlin_api.domain.entity.Area
import org.locationtech.jts.geom.Polygon

class AreaReturnDto(area: Area) {
    var id: Long = area.id
    var name: String = area.name
    var area: List<Point> = polygon2List(area.area)

    private fun polygon2List(polygon: Polygon): List<Point> {
        val points = mutableListOf<Point>()
        val coordinates = polygon.coordinates
        for (coordinate in coordinates) {
            points.add(Point(coordinate.getX(), coordinate.getY()))
        }
        return points;
    }
}
