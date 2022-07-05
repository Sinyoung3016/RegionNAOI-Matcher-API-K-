package com.sia.matcher_kotlin_api.controller.dto.response

import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto

class AreaListReturnResponse(areaReturnDtos: List<AreaReturnDto>) {
    var aois: List<AreaReturnDto> = areaReturnDtos
}
