package com.sia.matcher_kotlin_api.service

import com.sia.matcher_kotlin_api.AreaForTest
import com.sia.matcher_kotlin_api.respository.AOIRepository
import com.sia.matcher_kotlin_api.respository.RegionRepository
import com.sia.matcher_kotlin_api.service.dto.AreaReturnDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
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
    every {
        aoiRepository.findAllAOIByRegionId(1L)
    } returns AreaForTest.listOfAOI
    every {
        aoiRepository.findAllAOIByRegionId(2L)
    } returns listOf()

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

    given("readAllAOIInThisRegion") {
        `when`("If this region contains a num of aois") {
            val result = regionService.readAllAOIInThisRegion(1L)
            then("you will get a list of AOIs") {
                result.size shouldBe 3
            }
            then("and this type will be AreaReturnDto"){
                result[0].shouldBeTypeOf<AreaReturnDto>()
            }
        }
        `when`("If this region contains nothing") {
            val result = regionService.readAllAOIInThisRegion(2L)
            then("you will get NOTHING") {
                result.isEmpty().shouldBeTrue()
            }
        }
    }
})