package com.sia.matcher_kotlin_api.controller;

import com.sia.matcher_kotlin_api.fixture.RegionForTest.regionReturnDto
import com.sia.matcher_kotlin_api.fixture.RegionForTest.regionSaveRequest
import com.sia.matcher_kotlin_api.fixture.AreaForTest.listOfAOIReturnDto
import com.sia.matcher_kotlin_api.controller.dto.response.AreaListReturnResponse
import com.sia.matcher_kotlin_api.service.RegionService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class RegionControllerTest : BehaviorSpec({

    given("postRegion") {
        val regionService = mockk<RegionService>()
        every {
            regionService.createNewRegion(any())
        } returns regionReturnDto
        val regionController = RegionController(regionService)

        `when`("If you request to Save new Region") {
            val result = regionController.postRegion(regionSaveRequest)
            then("you can get new Region's id") {
                result.body!!.id shouldBe 0L
            }
            then("with 200 Status") {
                result.statusCode.is2xxSuccessful shouldBe true
            }
            then("verify") {
                verify(exactly = 1) { regionService.createNewRegion(any()) }
            }
        }
    }

    given("getAOIListInThisRegion") {
        val regionIdWithAOIS = 1L
        val regionIdWithNoAOI = 2L
        val regionIdNotInDB = 100L

        val regionService = mockk<RegionService>()
        every {
            regionService.hasRegionId(regionIdWithAOIS)
        } returns true
        every {
            regionService.hasRegionId(regionIdWithNoAOI)
        } returns true
        every {
            regionService.hasRegionId(regionIdNotInDB)
        } returns false
        every {
            regionService.readAllAOIInThisRegion(regionIdWithAOIS)
        } returns listOfAOIReturnDto
        every {
            regionService.readAllAOIInThisRegion(regionIdWithNoAOI)
        } returns listOf()
        val regionController = RegionController(regionService)

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
            then("data check") {
                result.body!!.aois[0].name shouldBe regionSaveRequest.name
            }
            then("verify") {
                verify(exactly = 1) { regionService.hasRegionId(regionIdWithAOIS) }
                verify(exactly = 1) { regionService.readAllAOIInThisRegion(regionIdWithAOIS) }
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
                result.statusCode.is2xxSuccessful.shouldBeTrue()
            }
            then("verify") {
                verify(exactly = 1) { regionService.hasRegionId(regionIdWithNoAOI) }
                verify(exactly = 1) { regionService.readAllAOIInThisRegion(regionIdWithNoAOI) }
            }
        }
        `when`("If We do not have Region 100") {
            val exception = shouldThrow<RuntimeException> {
                regionController.getAOIListInThisRegion("100")
            }
            then("Hello Exception!") {
                exception.message shouldBe "존재하지 않는 ID 입니다"
            }
            then("verify") {
                verify(exactly = 1) { regionService.hasRegionId(regionIdNotInDB) }
            }
        }
    }
})
