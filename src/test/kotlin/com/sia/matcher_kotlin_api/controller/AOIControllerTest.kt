package com.sia.matcher_kotlin_api.controller

import com.sia.matcher_kotlin_api.AreaForTest.Companion.aoiReturnDto
import com.sia.matcher_kotlin_api.AreaForTest.Companion.areaSaveRequest
import com.sia.matcher_kotlin_api.service.AOIService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class AOIControllerTest : BehaviorSpec({

    given("postAOI") {
        val aoiService = mockk<AOIService>()
        every {
            aoiService.createNewAOI(any())
        } returns aoiReturnDto

        val aoiController = AOIController(aoiService)
        `when`("If you request to Save new AOI") {
            val result = aoiController.postAOI(areaSaveRequest)
            then("you can get new AOI's id") {
                result.body!!.id shouldBe 0L
            }
            then("with 200 Status") {
                result.statusCode.is2xxSuccessful shouldBe true
            }
            then("verify") {
                verify(exactly = 1) { aoiService.createNewAOI(any()) }
            }
        }
    }
})