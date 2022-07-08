package com.sia.matcher_kotlin_api.service;

import com.sia.matcher_kotlin_api.fixture.AreaForTest.aoi
import com.sia.matcher_kotlin_api.fixture.AreaForTest.areaSaveDto
import com.sia.matcher_kotlin_api.respository.AOIRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class AOIServiceTest : BehaviorSpec({

    given("CreateNewAOI") {
        val aoiRepository = mockk<AOIRepository>()
        every {
            aoiRepository.save(any())
        } returns aoi
        val aoiService = AOIService(aoiRepository)

        `when`("If you add a new AOI") {
            val result = aoiService.createNewAOI(areaSaveDto)
            then("you can get this AOI in AOIRepository") {
                result.name shouldBe areaSaveDto.name
            }
            then("verify") {
                verify(exactly = 1) { aoiRepository.save(any()) }
            }
        }
    }
})