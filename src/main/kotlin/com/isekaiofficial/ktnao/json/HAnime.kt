package com.isekaiofficial.ktnao.json

import com.fasterxml.jackson.annotation.JsonProperty
import com.isekaiofficial.ktnao.json.common.Data
import com.isekaiofficial.ktnao.json.common.Header
import com.isekaiofficial.ktnao.json.common.SaucenaoResult

/**
 * {
 *       "header": {
 *         "similarity": "41.81",
 *         "thumbnail": "https:\/\/img3.saucenao.com\/vpc\/frame.php?expires=1709064000\u0026auth=29fUpgJFnyMo8odIh_Ft0hhQhd8\u0026rsz=0\u0026enc=YW5pbWUvQXNhcmktY2hhbi9b44Ki44OL44OhXSDjgYLjgZXjgorjgaHjgoPjgpMg56ysMzDoqbEg44CM44GC44GV44KK44Gh44KD44KT5a6H5a6Z44G46KGM44GP44CNLndtdi45Mjc1MS56aXA\u0026sub=4231-48-141277.jpg",
 *         "index_id": 21,
 *         "index_name": "Index #21: Anime* - 4231-48-141277.jpg (92751)",
 *         "dupes": 0,
 *         "hidden": 0
 *       },
 *       "data": {
 *         "ext_urls": [
 *           "https:\/\/anidb.net\/anime\/1926",
 *           "https:\/\/myanimelist.net\/anime\/6629\/",
 *           "https:\/\/anilist.co\/anime\/6629\/"
 *         ],
 *         "source": "Asari-chan",
 *         "anidb_aid": 1926,
 *         "mal_id": 6629,
 *         "anilist_id": 6629,
 *         "part": "30",
 *         "year": "1982-1983",
 *         "est_time": "00:02:21 \/ 00:24:23"
 *       }
 *     }
 */

data class HAnimeData(
    @JsonProperty("ext_urls")
    val extUrls: List<String>,
    val source: String,
    @JsonProperty("anidb_aid")
    val anidbAid: Int,
    @JsonProperty("mal_id")
    val malId: Int,
    @JsonProperty("anilist_id")
    val anilistId: Int,
    val part: String,
    val year: String,
    @JsonProperty("est_time")
    val estTime: String,
) : Data

class HAnimeResult(
    header: Header,
    data: HAnimeData,
) : SaucenaoResult(header, data)
