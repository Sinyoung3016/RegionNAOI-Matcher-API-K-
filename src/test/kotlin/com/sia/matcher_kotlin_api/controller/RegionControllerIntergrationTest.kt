package com.sia.matcher_kotlin_api.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.sia.matcher_kotlin_api.fixture.AreaForTest.name
import com.sia.matcher_kotlin_api.fixture.AreaForTest.pointsOfMap
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class RegionControllerIntergrationTest @Autowired constructor(val mockMvc: MockMvc) {

    @Test
    fun postRegionTest() {
        val uri = "/regions"
        val body = mapOf("name" to name, "area" to pointsOfMap)
        val content = jacksonObjectMapper().writeValueAsString(body)

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .content(content)
                .contentType(APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getAOIListInThisRegionTest_WeHaveAOIS() {
        val regionId = 5
        val uri = "/regions/$regionId/aois/intersects"

        mockMvc.perform(
            MockMvcRequestBuilders.get(uri)
                .contentType(APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.aois.length()").value(7))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getAOIListInThisRegionTest_WeDontHaveAOI() {
        val regionId = 2
        val uri = "/regions/$regionId/aois/intersects"

        mockMvc.perform(
            MockMvcRequestBuilders.get(uri)
                .contentType(APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.aois.length()").value(0))
            .andDo(MockMvcResultHandlers.print())
    }
}