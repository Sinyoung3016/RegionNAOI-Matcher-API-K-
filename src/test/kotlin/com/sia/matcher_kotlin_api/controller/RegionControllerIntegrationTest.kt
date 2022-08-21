package com.sia.matcher_kotlin_api.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.sia.matcher_kotlin_api.fixture.AreaForTest
import com.sia.matcher_kotlin_api.fixture.RegionForTest.name
import com.sia.matcher_kotlin_api.fixture.RegionForTest.pointsOfMap_haveAOIs
import com.sia.matcher_kotlin_api.fixture.RegionForTest.pointsOfMap_dontHaveAOIs
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class RegionControllerIntegrationTest @Autowired constructor(val mockMvc: MockMvc) {

    @Container
    private val postgresqlContainer = PostgreSQLContainer<Nothing>(
        DockerImageName.parse("postgis/postgis")
            .asCompatibleSubstituteFor("postgres")
    )

    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeTest(@Autowired mockMvc: MockMvc) {
            postAOIs(mockMvc)
            postRegion(mockMvc, pointsOfMap_haveAOIs)
            postRegion(mockMvc, pointsOfMap_dontHaveAOIs)
        }

        fun postAOIs(mockMvc: MockMvc) {
            val uri = "/aois"
            val body = mapOf("name" to AreaForTest.name, "area" to AreaForTest.pointsOfMap)
            val content = jacksonObjectMapper().writeValueAsString(body)

            mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                    .content(content)
                    .contentType(APPLICATION_JSON)
            )
        }

        fun postRegion(mockMvc: MockMvc, points: List<Map<String, Int>>) {
            val uri = "/regions"
            val body = mapOf("name" to name, "area" to points)
            val content = jacksonObjectMapper().writeValueAsString(body)

            mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                    .content(content)
                    .contentType(APPLICATION_JSON)
            )
        }
    }

    @Test
    fun postRegionTest() {
        val uri = "/regions"
        val body = mapOf("name" to name, "area" to pointsOfMap_dontHaveAOIs)
        val content = jacksonObjectMapper().writeValueAsString(body)

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .content(content)
                .contentType(APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getAOIListInThisRegionTest_WeHaveAOIS() {
        val regionId = 1
        val uri = "/regions/$regionId/aois/intersects"

        mockMvc.perform(
            MockMvcRequestBuilders.get(uri)
                .contentType(APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.aois.length()").value(1))
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