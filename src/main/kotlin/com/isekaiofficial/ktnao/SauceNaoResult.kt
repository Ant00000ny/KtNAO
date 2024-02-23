package com.isekaiofficial.ktnao

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

/**
 * {
 *     "header" : {
 *       "similarity" : "27.19",
 *       "thumbnail" : "https://img1.saucenao.com/res/pixiv/9722/manga/97222375_p2.jpg?auth=1rb08WFdC1ibYQu5aRGSTA&exp=1709064000",
 *       "index_id" : 5,
 *       "index_name" : "Index #5: Pixiv Images - 97222375_p2.jpg",
 *       "dupes" : 0,
 *       "hidden" : 0
 *     },
 *     "data" : {
 *       "ext_urls" : [ "https://www.pixiv.net/member_illust.php?mode=medium&illust_id=97222375" ],
 *       "title" : "Sui the Doctor",
 *       "pixiv_id" : 97222375,
 *       "member_name" : "Tani",
 *       "member_id" : 60698204
 *     }
 *   }
 */
data class SauceNaoResult(
    val similarity: String,
    val thumbnail: String,
    @JsonProperty("ext_urls")
    val urls: List<String>,
    val title: String,
    @JsonProperty("member_name")
    val memberName: String,
    @JsonProperty("member_id")
    val memberId: String,
    @JsonProperty("pixiv_id")
    val pixivId: String,
) {
    companion object {
        internal fun fromResponse(responseJsonNode: JsonNode): List<SauceNaoResult> {
            return responseJsonNode.at("/results")
                .filter { !it.isMissingNode }
                .map {
                    SauceNaoResult(
                        similarity = it.at("/header/similarity").asText(),
                        thumbnail = it.at("/header/thumbnail").asText(),
                        urls = it.at("/data/ext_urls").map(JsonNode::asText),
                        title = it.at("/data/title").asText(),
                        memberName = it.at("/data/member_name").asText(),
                        memberId = it.at("/data/member_id").asText(),
                        pixivId = it.at("/data/pixiv_id").asText()
                    )
                }
        }
    }
}
