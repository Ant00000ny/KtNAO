package com.isekaiofficial.ktnao.json.common

import com.fasterxml.jackson.annotation.JsonProperty

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
