package com.sia.matcher_kotlin_api.controller;

import com.sia.matcher_kotlin_api.AreaForTest.Companion.aoiReturnDto
import com.sia.matcher_kotlin_api.AreaForTest.Companion.areaSaveRequest
import com.sia.matcher_kotlin_api.AreaForTest.Companion.listOfAOIReturnDto
import com.sia.matcher_kotlin_api.controller.dto.response.AreaListReturnResponse
import com.sia.matcher_kotlin_api.service.RegionService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk

class RegionControllerTest : BehaviorSpec({

    val regionService = mockk<RegionService>()
    every {
        regionService.createNewRegion(any())
    } returns aoiReturnDto
    every {
        regionService.hasRegionId(1L)
    } returns true
    every {
        regionService.hasRegionId(2L)
    } returns true
    every {
        regionService.hasRegionId(100L)
    } returns false
    every {
        regionService.readAllAOIInThisRegion(1L)
    } returns listOfAOIReturnDto
    every {
        regionService.readAllAOIInThisRegion(2L)
    } returns listOf()

    val regionController = RegionController(regionService)

    given("postRegion") {
        `when`("If you request to Save new Region") {
            val result = regionController.postRegion(areaSaveRequest)
            then("you can get new Region's id") {
                result.body!!.id shouldBe 0L
            }
            then("with 200 Status") {
                result.statusCode.is2xxSuccessful
            }
        }
    }

    given("getAOIListInThisRegion") {
        `when`("If We have Region 1 and this region contains list of AOI") {
            val result = regionController.getAOIListInThisRegion("1")
            then("you will get 3 AOIs") {
                result.body!!.aois.size shouldBe 3
            }
            then("and this type will be a AreaListReturnResponse") {
                result.body.shouldBeTypeOf<AreaListReturnResponse>()
            }
            then("with 200 Status") {
                result.statusCode.is2xxSuccessful
            }
        }
        `when`("If We have Region 1 but this region contains nothing") {
            val result = regionController.getAOIListInThisRegion("2")
            then("you will get a empty List") {
                result.body!!.aois.isEmpty().shouldBeTrue()
            }
            then("but this type will be a AreaListReturnResponse") {
                result.body.shouldBeTypeOf<AreaListReturnResponse>()
            }
            then("with 200 Status") {
                result.statusCode.is2xxSuccessful
            }
        }
        `when`("If We do not have Region 100") {
            val exception = shouldThrow<RuntimeException> {
                regionController.getAOIListInThisRegion("100")
            }
            then("Hello Exception!") {
               exception.message shouldBe "존재하지 않는 ID 입니다"
            }
        }
    }
})
