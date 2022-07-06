package com.sia.matcher_kotlin_api.controller;

import com.sia.matcher_kotlin_api.AreaForTest
import com.sia.matcher_kotlin_api.service.RegionService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class RegionControllerTest: BehaviorSpec({
    val areaSaveRequest = AreaForTest.areaSaveRequest

    val regionService = mockk<RegionService>()
    every {
        regionService.createNewRegion(any())
    } returns AreaForTest.aoiReturnDto

    val regionController = RegionController(regionService)

    given("postRegion"){
        `when`("If you request to Save new Region"){
            val result = regionController.postRegion(areaSaveRequest)
            then("you can get new Region's id") {
                result.body!!.id shouldBe 0L
            }
            then("with 200 Status") {
                result.statusCode.is2xxSuccessful
            }
        }
    }
})
