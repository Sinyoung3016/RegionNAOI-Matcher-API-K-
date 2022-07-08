package com.sia.matcher_kotlin_api.service

import com.sia.matcher_kotlin_api.fixture.AreaForTest.areaSaveDto
import com.sia.matcher_kotlin_api.fixture.AreaForTest.listOfAOI
import com.sia.matcher_kotlin_api.fixture.AreaForTest.region
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
import io.mockk.verify

class RegionServiceTest : BehaviorSpec({

    given("CreateNewRegion") {
        val regionRepository = mockk<RegionRepository>()
        every {
            regionRepository.save(any())
        } returns region
        val aoiRepository = mockk<AOIRepository>()
        val regionService = RegionService(regionRepository, aoiRepository)

        `when`("If you add a new Region") {
            val result = regionService.createNewRegion(areaSaveDto)
            then("you can get this AOI in AOIRepository") {
                result.name shouldBe areaSaveDto.name
            }
            then("verify") {
                verify(exactly = 1) { regionRepository.save(any()) }
            }
        }
    }

    given("hasRegionId") {
        val regionIdInDB = 1L
        val regionIdNotInDB = 100L

        val regionRepository = mockk<RegionRepository>()
        every {
            regionRepository.existsById(regionIdNotInDB)
        } returns false
        every {
            regionRepository.existsById(regionIdInDB)
        } returns true
        val aoiRepository = mockk<AOIRepository>()
        val regionService = RegionService(regionRepository, aoiRepository)

        `when`("We have Region 1") {
            val result = regionService.hasRegionId(regionIdInDB)
            then("you will get true") {
                result.shouldBeTrue()
            }
            then("verify") {
                verify(exactly = 1) { regionRepository.existsById(regionIdInDB) }
            }
        }
        `when`("We do not have Region 100") {
            val result = regionService.hasRegionId(regionIdNotInDB)
            then("you will get false") {
                result.shouldBeFalse()
            }
            then("verify") {
                verify(exactly = 1) { regionRepository.existsById(regionIdNotInDB) }
            }
        }
    }

    given("readAllAOIInThisRegion") {
        val regionIdWithAOIS = 1L
        val regionIdWithNoAOI = 2L

        val regionRepository = mockk<RegionRepository>()
        val aoiRepository = mockk<AOIRepository>()
        every {
            aoiRepository.findAllAOIByRegionId(regionIdWithAOIS)
        } returns listOfAOI
        every {
            aoiRepository.findAllAOIByRegionId(regionIdWithNoAOI)
        } returns listOf()
        val regionService = RegionService(regionRepository, aoiRepository)

        `when`("If this region contains a num of aois") {
            val result = regionService.readAllAOIInThisRegion(regionIdWithAOIS)
            then("you will get a list of AOIs") {
                result.size shouldBe 3
            }
            then("and this type will be AreaReturnDto") {
                result[0].shouldBeTypeOf<AreaReturnDto>()
            }
            then("data check") {
                result[0].name shouldBe areaSaveDto.name
            }
            then("verify") {
                verify(exactly = 1) { aoiRepository.findAllAOIByRegionId(regionIdWithAOIS) }
            }
        }
        `when`("If this region contains nothing") {
            val result = regionService.readAllAOIInThisRegion(regionIdWithNoAOI)
            then("you will get NOTHING") {
                result.isEmpty().shouldBeTrue()
            }
            then("verify") {
                verify(exactly = 1) { aoiRepository.findAllAOIByRegionId(regionIdWithNoAOI) }
            }
        }
    }
})