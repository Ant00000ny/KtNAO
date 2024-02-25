package com.isekaiofficial.ktnao

import com.isekaiofficial.ktnao.ApiParams.API_KEY
import com.isekaiofficial.ktnao.ApiParams.DB
import com.isekaiofficial.ktnao.ApiParams.DB_MASK
import com.isekaiofficial.ktnao.ApiParams.DB_MASK_INVERT
import com.isekaiofficial.ktnao.ApiParams.DEDUPLICATE
import com.isekaiofficial.ktnao.ApiParams.HIDE
import com.isekaiofficial.ktnao.ApiParams.MIN_SIM
import com.isekaiofficial.ktnao.ApiParams.NUMBER_OF_RESULTS
import com.isekaiofficial.ktnao.ApiParams.OUTPUT_TYPE
import com.isekaiofficial.ktnao.ApiParams.URL
import com.isekaiofficial.ktnao.enums.SaucenaoDbEnum
import com.isekaiofficial.ktnao.json.common.SaucenaoResult
import com.isekaiofficial.ktnao.util.API_URL
import com.isekaiofficial.ktnao.util.client
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class SaucenaoClient(
    private val apiKey: String,
    private val dbMask: DbIndex? = null,
    private val dbMaskI: DbIndex? = null,
    private val db: SaucenaoDbEnum? = null,
    private val numres: Int = 1,
    private val minsim: Int = 70,
    private val dedupe: ApiParams.Deduplicate = ApiParams.Deduplicate.DEDUPE_ALL,
    private val hide: ApiParams.Hide = ApiParams.Hide.SHOW_ALL,
) {
    init {
        require(listOfNotNull(dbMask, dbMaskI, db).size == 1) { "you must provide one of 'dbMask', 'dbMaskI' or 'db' param. " }
    }

    suspend fun request(
        imageUrl: String? = null,
        imageBytes: ByteArray? = null,
    ): List<SaucenaoResult> {
        require(listOfNotNull(imageUrl, imageBytes).size == 1) { "you must provide one of 'imageUrl' or 'imageBytes' param. " }

        val url = URLBuilder().apply {
            takeFrom(API_URL)
            listOf(
                API_KEY to apiKey,
                OUTPUT_TYPE to ApiParams.OutputType.JSON.value, // always expect json output
                NUMBER_OF_RESULTS to numres.toString(),
                MIN_SIM to minsim.toString(),
                DB to db?.index.toString(),
                DB_MASK to dbMask?.getValue().toString(),
                DB_MASK_INVERT to dbMaskI?.getValue().toString(),
                DEDUPLICATE to dedupe.value,
                HIDE to hide.value,
                URL to imageUrl,
            )
                .filter { it.second != null && it.second != "null" }
                .forEach { (key, value) -> parameters.append(key, value!!) }

        }.buildString()


        val resp = if (imageUrl != null) {
            client.get(url)
        } else {
            client.submitFormWithBinaryData(
                url = url,
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
