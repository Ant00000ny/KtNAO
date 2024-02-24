package com.isekaiofficial.ktnao.json

import com.fasterxml.jackson.annotation.JsonProperty
import com.isekaiofficial.ktnao.json.common.Data
import com.isekaiofficial.ktnao.json.common.Header
import com.isekaiofficial.ktnao.json.common.SaucenaoResult

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

class DanbooruResult(
    header: Header,
    data: DanbooruData,
) : SaucenaoResult(header, data)
