package com.isekaiofficial.ktnao.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.*
import io.ktor.client.engine.cio.*

internal val client = HttpClient(CIO) {
    expectSuccess = true
}

internal const val API_URL = "https://saucenao.com/search.php"

internal val objectMapper = jacksonObjectMapper()
