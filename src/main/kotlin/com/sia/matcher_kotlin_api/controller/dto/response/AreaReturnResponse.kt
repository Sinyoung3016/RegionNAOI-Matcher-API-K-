package com.sia.matcher_kotlin_api.controller.dto.response

import com.sia.matcher_kotlin_api.domain.Point
import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto

class AreaReturnResponse(returnDto: AreaReturnDto) {
    var id: Long = returnDto.id
    var name: String = returnDto.name
    var area: List<Point> = returnDto.area
}
