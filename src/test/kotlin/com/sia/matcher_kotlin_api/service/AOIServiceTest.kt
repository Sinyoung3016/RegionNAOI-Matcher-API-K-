package com.sia.matcher_kotlin_api.service;

import com.sia.matcher_kotlin_api.AreaForTest
import com.sia.matcher_kotlin_api.respository.AOIRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AOIServiceTest : BehaviorSpec({
    val areaSaveDto = AreaForTest.areaSaveDto

    val aoiRepository = mockk<AOIRepository>()
    every {
        aoiRepository.save(any())
    } returns AreaForTest.aoi

    val aoiService = AOIService(aoiRepository)

    given("CreateNewAOI") {
        `when`("If you add a new AOI") {
            val result = aoiService.createNewAOI(areaSaveDto)
            then("you can get this AOI in AOIRepository") {
                result.name shouldBe areaSaveDto.name
            }
        }
    }
})