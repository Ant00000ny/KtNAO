package com.isekaiofficial.ktnao

import com.isekaiofficial.ktnao.ApiParams.DB
import com.isekaiofficial.ktnao.ApiParams.MIN_SIM
import com.isekaiofficial.ktnao.ApiParams.NUMBER_OF_RESULTS
import com.isekaiofficial.ktnao.ApiParams.OUTPUT_TYPE
import com.isekaiofficial.ktnao.ApiParams.URL
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class SaucenaoClient(private val apiKey: String) {
    fun request(
        imageUrl: String? = null,
        imageBytes: ByteArray? = null,
        numres: Int = 1,
        minsim: Int = 52,
    ): List<SaucenaoResult> {
        require(imageUrl != null || imageBytes != null) { "Either imageUrl or imageBytes must be provided" }

        val isUrl = imageUrl != null


        val url = API_URL.toHttpUrl()
            .newBuilder()
            .addQueryParameter(ApiParams.API_KEY, apiKey)
            .addQueryParameter(OUTPUT_TYPE, ApiParams.OutputType.JSON.value)
            .addQueryParameter(NUMBER_OF_RESULTS, numres.toString())
            .addQueryParameter(MIN_SIM, minsim.toString())
            .addQueryParameter(DB, "999")
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

        return SaucenaoResult.fromResponse(responseJsonNode)
    }
}
