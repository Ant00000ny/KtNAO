package com.isekaiofficial.ktnao

import com.isekaiofficial.ktnao.ApiParams.API_KEY
import com.isekaiofficial.ktnao.ApiParams.DB
import com.isekaiofficial.ktnao.ApiParams.MIN_SIM
import com.isekaiofficial.ktnao.ApiParams.NUMBER_OF_RESULTS
import com.isekaiofficial.ktnao.ApiParams.OUTPUT_TYPE
import com.isekaiofficial.ktnao.ApiParams.URL
import com.isekaiofficial.ktnao.json.common.SaucenaoResult
import com.isekaiofficial.ktnao.util.API_URL
import com.isekaiofficial.ktnao.util.client
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class SauceNaoClient111(private val apiKey: String) {
    suspend fun request(
        imageUrl: String? = null,
        imageBytes: ByteArray? = null,
        numres: Int = 1,
        minsim: Int = 52,
    ): List<SaucenaoResult> {
        require(imageUrl != null || imageBytes != null) { "Either imageUrl or imageBytes must be provided" }

        val resp = if (imageUrl != null) {
            client.get {
                url {
                    takeFrom(url)
                    parameters {
                        API_KEY to apiKey
                        OUTPUT_TYPE to ApiParams.OutputType.JSON.value
                        NUMBER_OF_RESULTS to numres.toString()
                        MIN_SIM to minsim.toString()
                        DB to "999"
                        URL to imageUrl
                    }
                }
            }
        } else {
            client.submitFormWithBinaryData(
                url = API_URL,
                formData = formData {
                    append(
                        "file",
                        imageBytes!!,
                        Headers.build { append(HttpHeaders.ContentDisposition, "filename=image") }
                    )
                }
            )
        }

        val json = resp.body<String>()
        return SaucenaoResult.fromResponse(json)
    }
}
