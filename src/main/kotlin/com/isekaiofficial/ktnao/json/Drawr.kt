package com.isekaiofficial.ktnao.json

import com.fasterxml.jackson.annotation.JsonProperty
import com.isekaiofficial.ktnao.json.common.Data
import com.isekaiofficial.ktnao.json.common.Header
import com.isekaiofficial.ktnao.json.common.SaucenaoResult

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

class DrawrResult(
    header: Header,
    data: DrawrData,
) : SaucenaoResult(header, data)
