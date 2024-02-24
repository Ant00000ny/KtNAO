package com.isekaiofficial.ktnao.json.common

import com.isekaiofficial.ktnao.enums.SaucenaoDbEnum
import com.isekaiofficial.ktnao.util.objectMapper

open class SaucenaoResult(
    val header: Header,
    val data: Data,
) {
    companion object {
        fun fromResponse(json: String): List<SaucenaoResult> {
            val resultsNode = objectMapper.readTree(json)
                .at("/results")

            return resultsNode.map { resultNode ->
                val dbIndex = SaucenaoDbEnum.fromIndex(resultsNode.at("/header/index_id").asInt())
                    ?: throw IllegalArgumentException("Invalid db index")
                objectMapper.convertValue(resultNode, dbIndex.resultClass)
            }
        }
    }
}
