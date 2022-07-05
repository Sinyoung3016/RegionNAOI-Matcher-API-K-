package com.sia.matcher_kotlin_api.controller.dto.response

import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto

class AreaSaveResponse(returnDto: AreaReturnDto) {
    var id: Long = returnDto.id
}