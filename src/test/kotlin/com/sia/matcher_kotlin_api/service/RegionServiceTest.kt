package com.sia.matcher_kotlin_api.service

import com.sia.matcher_kotlin_api.AreaForTest
import com.sia.matcher_kotlin_api.respository.AOIRepository
import com.sia.matcher_kotlin_api.respository.RegionRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class RegionServiceTest : BehaviorSpec({
    val areaSaveDto = AreaForTest.areaSaveDto

    val regionRepository = mockk<RegionRepository>()
    every {
        regionRepository.save(any())
    } returns AreaForTest.region
    every {
        regionRepository.existsById(100L)
    } returns false
    every {
        regionRepository.existsById(1L)
    } returns true

    val aoiRepository = mockk<AOIRepository>()
    val regionService = RegionService(regionRepository, aoiRepository)

    given("CreateNewRegion") {
        `when`("If you add a new Region") {
            val result = regionService.createNewRegion(areaSaveDto)
            then("you can get this AOI in AOIRepository") {
                result.name shouldBe areaSaveDto.name
            }
        }
    }

    given("hasRegionId") {
        `when`("We have Region 1") {
            val result = regionService.hasRegionId(1L)
            then("you will get true") {
                result.shouldBeTrue()
            }
        }
        `when`("We do not have Region 100") {
            val result = regionService.hasRegionId(100L)
            then("you will get false") {
                result.shouldBeFalse()
            }
        }
    }
})