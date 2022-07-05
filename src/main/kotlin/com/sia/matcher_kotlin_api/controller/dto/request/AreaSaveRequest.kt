package com.sia.matcher_kotlin_api.controller.dto.request

import com.sia.matcher_kotlin_api.domain.Point

class AreaSaveRequest {
    lateinit var name: String
    lateinit var area: List<Point>
}
