package com.isekaiofficial.ktnao

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

class SaucenaoResult(
    val header: Header,
    val data: Data,
) {
    companion object {
        fun fromResponse(responseJsonNode: JsonNode): List<SaucenaoResult> {
            val classes = listOf(
                PixivData::class.java,
                TwitterData::class.java,
                DanbooruData::class.java,
                DrawrData::class.java,
            )
            val saucenaoResults = responseJsonNode.at("/results")
                .map {
                    val dataNode = it.at("/data")
                    val data = classes.firstNotNullOf { clazz ->
                        try {
                            objectMapper.convertValue(dataNode, clazz)
                        } catch (e: Exception) {
                            null
                        }
                    }
                    val header = objectMapper.convertValue(it.at("/header"), Header::class.java)
                    SaucenaoResult(header, data)
                }

            return saucenaoResults
        }
    }

    interface Data

    data class Header(
        val similarity: String,
        val thumbnail: String,
        @JsonProperty("index_id")
        val indexId: String,
        @JsonProperty("index_name")
        val indexName: String,
        val dupes: String,
        val hidden: String,
    )

    data class PixivData(
        @JsonProperty("ext_urls")
        val extUrls: List<String>,
        val title: String,
        @JsonProperty("pixiv_id")
        val pixivId: String,
        @JsonProperty("member_name")
        val memberName: String,
        @JsonProperty("member_id")
        val memberId: String,
    ) : Data

    data class DanbooruData(
        @JsonProperty("ext_urls")
        val extUrls: List<String>,
        @JsonProperty("danbooru_id")
        val danbooruId: String,
        @JsonProperty("gelbooru_id")
        val gelbooruId: String,
        val creator: String,
        val material: String,
        val characters: String,
        val source: String,
    ) : Data

    data class DrawrData(
        @JsonProperty("ext_urls")
        val extUrls: List<String>,
        val title: String,
        @JsonProperty("drawr_id")
        val drawrId: String,
        @JsonProperty("member_name")
        val memberName: String,
        @JsonProperty("member_id")
        val memberId: String,
    ) : Data

    data class TwitterData(
        @JsonProperty("ext_urls")
        val extUrls: List<String>,
        @JsonProperty("created_at")
        val createdAt: String,
        @JsonProperty("tweet_id")
        val tweetId: String,
        @JsonProperty("twitter_user_id")
        val twitterUserId: String,
        @JsonProperty("twitter_user_handle")
        val twitterUserHandle: String,
    ) : Data
}
