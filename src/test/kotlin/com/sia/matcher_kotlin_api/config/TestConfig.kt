package com.sia.matcher_kotlin_api.config

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.extensions.spring.SpringExtension

object TestConfig : AbstractProjectConfig() {
    override fun extensions(): List<Extension> {
        return listOf(SpringExtension)
    }
}