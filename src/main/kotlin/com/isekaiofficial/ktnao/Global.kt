package com.isekaiofficial.ktnao

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient

internal val client = OkHttpClient
    .Builder()
    .build()

internal const val API_URL = "https://saucenao.com/search.php"

internal val objectMapper = jacksonObjectMapper()

enum class SauceNaoDbEnum(val indexName: String) {
    INDEX_HMAGS("index_hmags"),
    INDEX_RESERVED1("index_reserved"),
    INDEX_HCG("index_hcg"),
    INDEX_DDBOBJECTS("index_ddbobjects"),
    INDEX_DDBSAMPLES("index_ddbsamples"),
    INDEX_PIXIV("index_pixiv"), // *
    INDEX_PIXIVHISTORICAL("index_pixivhistorical"), // *
    INDEX_RESERVED2("index_reserved"),
    INDEX_SEIGAILLUST("index_seigaillust"), // *
    INDEX_DANBOORU("index_danbooru"),
    INDEX_DRAWR("index_drawr"), // *
    INDEX_NIJIE("index_nijie"), // *
    INDEX_YANDERE("index_yandere"),
    INDEX_ANIMEOP("index_animeop"),
    INDEX_RESERVED3("index_reserved"),
    INDEX_SHUTTERSTOCK("index_shutterstock"),
    INDEX_FAKKU("index_fakku"),
    INDEX_HMISC("index_hmisc"),
    INDEX_2DMARKET("index_2dmarket"),
    INDEX_MEDIBANG("index_medibang"),
    INDEX_ANIME("index_anime"),
    INDEX_HANIME("index_hanime"),
    INDEX_MOVIES("index_movies"),
    INDEX_SHOWS("index_shows"),
    INDEX_GELBOORU("index_gelbooru"),
    INDEX_KONACHAN("index_konachan"),
    INDEX_SANKAKU("index_sankaku"),
    INDEX_ANIMEPICTURES("index_animepictures"),
    INDEX_E621("index_e621"),
    INDEX_IDOLCOMPLEX("index_idolcomplex"),
    INDEX_BCYILLUST("index_bcyillust"),
    INDEX_BCYCOSPLAY("index_bcycosplay"),
    INDEX_PORTALGRAPHICS("index_portalgraphics"),
    INDEX_DA("index_da"), // *
    INDEX_PAWOO("index_pawoo"),
    INDEX_MADOKAMI("index_madokami"),
    INDEX_MANGADEX("index_mangadex"),
}

class DbIndex {
    private val indices = SauceNaoDbEnum
        .entries
        .associateWith { false }
        .toMutableMap()

    fun enable(vararg index: SauceNaoDbEnum): DbIndex {
        index.forEach {
            indices[it] = true
        }

        return this
    }

    fun disable(vararg index: SauceNaoDbEnum): DbIndex {
        index.forEach {
            indices[it] = false
        }

        return this
    }

    fun enableAll(): DbIndex {
        indices.replaceAll { _, _ -> true }
        return this
    }

    fun getValue(): Long {
        return indices
            .values
            .joinToString("") { if (it) "1" else "0" }
            .reversed()
            .toLong(2)
    }
}
