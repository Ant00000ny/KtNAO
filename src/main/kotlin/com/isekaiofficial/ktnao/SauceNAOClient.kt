package com.isekaiofficial.ktnao

import com.isekaiofficial.ktnao.ApiParams.DB_MASK
import com.isekaiofficial.ktnao.ApiParams.MIN_SIM
import com.isekaiofficial.ktnao.ApiParams.NUMBER_OF_RESULTS
import com.isekaiofficial.ktnao.ApiParams.OUTPUT_TYPE
import com.isekaiofficial.ktnao.ApiParams.URL
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.nio.file.Paths
import kotlin.io.path.readBytes

class SauceNAOClient(private val apiKey: String) {
    fun request(
        imageUrl: String? = null,
        imageBytes: ByteArray? = null,
        dbIndex: DbIndex = DbIndex().enableAll(),
        numres: Int = 1,
        minsim: Int = 80,
    ): List<SauceNaoResult> {

        require(imageUrl != null || imageBytes != null) { "Either imageUrl or imageBytes must be provided" }

        val isUrl = imageUrl != null


        val url = API_URL.toHttpUrl()
            .newBuilder()
            .addQueryParameter(ApiParams.API_KEY, apiKey)
            .addQueryParameter(OUTPUT_TYPE, ApiParams.OutputType.JSON.value)
            .addQueryParameter(NUMBER_OF_RESULTS, numres.toString())
            .addQueryParameter(MIN_SIM, minsim.toString())
            .addQueryParameter(DB_MASK, dbIndex.getValue().toString())
            .apply { if (isUrl) addQueryParameter(URL, imageUrl) }
            .build()

        val req = if (isUrl) {
            Request.Builder()
                .url(url)
                .get()
                .build()
        } else {
            val body = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "image", imageBytes!!.toRequestBody())
                .build()
            Request.Builder()
                .url(url)
                .post(body)
                .build()
        }

        val responseJsonNode = client
            .newCall(req)
            .execute()
            .body
            .string()
            .let { objectMapper.readTree(it) }

        return SauceNaoResult.fromResponse(responseJsonNode)
    }
}

fun main() {
    val client = SauceNAOClient("your-api-key")
    val result = client.request(imageBytes = Paths.get("image.png").readBytes())
}
