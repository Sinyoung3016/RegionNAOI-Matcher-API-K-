package com.sia.matcher_kotlin_api.controller

import com.sia.matcher_kotlin_api.AreaForTest
import com.sia.matcher_kotlin_api.service.AOIService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AOIControllerTest : BehaviorSpec({
    val areaSaveRequest = AreaForTest.areaSaveRequest

    val aoiService = mockk<AOIService>()
    every {
        aoiService.createNewAOI(any())
    } returns AreaForTest.aoiReturnDto

    val aoiController = AOIController(aoiService)

    given("postAOI") {
        `when`("If you request to Save new AOI") {
            val result = aoiController.postAOI(areaSaveRequest)
            then("you can get new AOI's id") {
                result.body!!.id shouldBe 0L
            }
            then("with 200 Status") {
                result.statusCode.is2xxSuccessful
            }
        }
    }
})