package com.isekaiofficial.ktnao.json

import com.fasterxml.jackson.annotation.JsonProperty
import com.isekaiofficial.ktnao.json.common.Data
import com.isekaiofficial.ktnao.json.common.Header
import com.isekaiofficial.ktnao.json.common.SaucenaoResult

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

class TwitterResult(
    header: Header,
    data: TwitterData,
) : SaucenaoResult(header, data)
